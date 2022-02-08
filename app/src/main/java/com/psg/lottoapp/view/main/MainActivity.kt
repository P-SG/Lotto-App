package com.psg.lottoapp.view.main

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import com.psg.lottoapp.R
import com.psg.lottoapp.data.model.LottoEntity
import com.psg.lottoapp.data.model.LottoNum
import com.psg.lottoapp.databinding.ActivityMainBinding
import com.psg.lottoapp.util.AppLogger
import com.psg.lottoapp.view.base.BaseActivity
import com.psg.lottoapp.view.generate.GeneNumActivity
import com.psg.lottoapp.view.qrscan.QRScanActivity
import com.psg.lottoapp.view.qrscan.QRScanViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(R.layout.activity_main) {
    override val TAG: String = MainActivity::class.java.simpleName
    override val viewModel: MainViewModel by inject()

    private val lotto: LottoNum = LottoNum("","",0,0,0,0,0,0,0,0,0,0,0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()

    }

    private fun checkOverTime(num: Int){
        val b = intent.getBooleanExtra("overTime",false)
        println("b체크 $b")
        if (b) viewModel.searchLotto(num+1) else viewModel.searchLotto(num)
    }

    private fun initView(){

        binding.etLottoNum.addTextChangedListener(textWatcher)

        viewModel.lottoEntity.observe(this, {
            if (it != null){ // DB에 저장된 로또회차가 있을 때
                binding.etLottoNum.setText(it.drwNo.toString())
                binding.tvLottoNum.text = "회차 당첨번호 ${it.drwNoDate.replace("-",".")}"
                viewModel.searchLotto(it.drwNo)
//                checkOverTime(it.drwNo)
                viewModel.lottoNum.observe(this,{ num ->
                    if (num != null){
                        binding.tvLottoNum1.text = num.drwtNo1.toString()
                        binding.tvLottoNum2.text = num.drwtNo2.toString()
                        binding.tvLottoNum3.text = num.drwtNo3.toString()
                        binding.tvLottoNum4.text = num.drwtNo4.toString()
                        binding.tvLottoNum5.text = num.drwtNo5.toString()
                        binding.tvLottoNum6.text = num.drwtNo6.toString()
                        binding.tvLottoBns.text = num.bnusNo.toString()
                        binding.tvLottoNum1.backgroundTintList = ColorStateList.valueOf(resources.getColor(parseColor(num.drwtNo1)))
                        binding.tvLottoNum2.backgroundTintList = ColorStateList.valueOf(resources.getColor(parseColor(num.drwtNo2)))
                        binding.tvLottoNum3.backgroundTintList = ColorStateList.valueOf(resources.getColor(parseColor(num.drwtNo3)))
                        binding.tvLottoNum4.backgroundTintList = ColorStateList.valueOf(resources.getColor(parseColor(num.drwtNo4)))
                        binding.tvLottoNum5.backgroundTintList = ColorStateList.valueOf(resources.getColor(parseColor(num.drwtNo5)))
                        binding.tvLottoNum6.backgroundTintList = ColorStateList.valueOf(resources.getColor(parseColor(num.drwtNo6)))
                        binding.tvLottoBns.backgroundTintList = ColorStateList.valueOf(resources.getColor(parseColor(num.bnusNo)))

                    }
                })
            } else { // DB에 저장된 로또회차가 없을 때
                AppLogger.p("로또번호가 null")
                binding.etLottoNum.setText("1001")
                binding.tvLottoNum.text = "회차 당첨번호 2022.02.05"
                viewModel.searchLotto(1001)
                viewModel.lottoNum.observe(this, { num ->
                    if (num != null){ // 검색한 로또회차가 있을 때
                        println("INSERT 호출")
                        viewModel.insertLotto(LottoEntity(num.drwNo,num.drwNoDate))
                    } else { // 검색한 로또회차가 없을 때
                        println("api가 null")
                    }
                })
            }
        })


        binding.btnQrScan.setOnClickListener {
            val intent = Intent(this,QRScanActivity::class.java)
            startActivity(intent)
        }

    }

    private fun parseColor(num: Int) = when(num){
        in 1..10 -> R.color.num1
        in 11..20 -> R.color.num2
        in 21..30 -> R.color.num3
        in 31..40 -> R.color.num4
        in 41..45 -> R.color.num5
        else -> R.color.black
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s!!.length > 1) {
                viewModel.searchLotto(s.toString().toInt())
            }
        }

    }


}