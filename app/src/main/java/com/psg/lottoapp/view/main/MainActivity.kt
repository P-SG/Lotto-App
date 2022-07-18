package com.psg.lottoapp.view.main

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import com.psg.lottoapp.R
import com.psg.data.model.local.LottoEntity
import com.psg.domain.model.LottoDate
import com.psg.lottoapp.databinding.ActivityMainBinding
import com.psg.lottoapp.util.AppLogger
import com.psg.lottoapp.view.base.BaseActivity
import com.psg.lottoapp.view.generate.GeneNumActivity
import com.psg.lottoapp.view.qrscan.QRScanActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(R.layout.activity_main) {
    override val TAG: String = MainActivity::class.java.simpleName
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        binding.lifecycleOwner = this

    }

    private fun checkOverTime(num: Int){
        val b = intent.getBooleanExtra("overTime",false)
        AppLogger.println("b체크 $b")
        if (b) viewModel.getRemoteLotto(num+1) else viewModel.getRemoteLotto(num)
    }

    private fun initView(){
        viewModel.getLocalLotto()

        binding.etLottoNum.addTextChangedListener(textWatcher)

        viewModel.lottoDate.observe(this) {
            if (it != null) { // DB에 저장된 로또회차가 있을 때
                binding.etLottoNum.setText(it.drwNo.toString())
                binding.tvLottoNum.text = "회차 당첨번호 ${it.drwNoDate.replace("-", ".")}"
                viewModel.getRemoteLotto(it.drwNo)
//                checkOverTime(it.drwNo)
                viewModel.lottoNum.observe(this) { num ->
                    if (num != null) {
                        binding.item = num
                        binding.tvLottoNum1.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(viewModel.parseColor(num.drwtNo1)))
                        binding.tvLottoNum2.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(viewModel.parseColor(num.drwtNo2)))
                        binding.tvLottoNum3.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(viewModel.parseColor(num.drwtNo3)))
                        binding.tvLottoNum4.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(viewModel.parseColor(num.drwtNo4)))
                        binding.tvLottoNum5.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(viewModel.parseColor(num.drwtNo5)))
                        binding.tvLottoNum6.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(viewModel.parseColor(num.drwtNo6)))
                        binding.tvLottoBns.backgroundTintList =
                            ColorStateList.valueOf(resources.getColor(viewModel.parseColor(num.bnusNo)))

                    }
                }
            } else { // DB에 저장된 로또회차가 없을 때
                AppLogger.println("로또번호가 null")
                binding.etLottoNum.setText("1001")
                binding.tvLottoNum.text = "회차 당첨번호 2022.02.05"
                viewModel.getRemoteLotto(1001)
                viewModel.lottoNum.observe(this) { num ->
                    if (num != null) { // 검색한 로또회차가 있을 때
                        AppLogger.println("INSERT 호출")
                        viewModel.insertLotto(
                            LottoDate(
                                num.drwNo,
                                num.drwNoDate
                            )
                        )
                    } else { // 검색한 로또회차가 없을 때
                        AppLogger.println("api가 null")
                    }
                }
            }
        }


        binding.btnQrScan.setOnClickListener {
            val intent = Intent(this,QRScanActivity::class.java)
            startActivity(intent)
        }

        binding.btnGenerateNum.setOnClickListener {
            val intent = Intent(this,GeneNumActivity::class.java)
            startActivity(intent)
        }

    }


    private val textWatcher = object : TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s!!.length > 1){
                viewModel.getRemoteLotto(s.toString().toInt())
            }
        }

    }


}