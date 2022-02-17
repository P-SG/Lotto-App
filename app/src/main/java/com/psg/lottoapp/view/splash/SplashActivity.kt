package com.psg.lottoapp.view.splash

import android.content.Intent
import android.os.Bundle
import com.psg.lottoapp.R
import com.psg.lottoapp.data.model.LottoEntity
import com.psg.lottoapp.databinding.ActivitySplashBinding
import com.psg.lottoapp.view.base.BaseActivity
import com.psg.lottoapp.view.main.MainActivity
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>(R.layout.activity_splash) {
    override val TAG: String = MainActivity::class.java.simpleName
    override val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNewDrwNo()
    }

    private fun checkNewDrwNo(){
        val intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

        viewModel.lottoEntity.observe(this,{
            if (it != null){
                val sf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
                val date = sf.parse("${it.drwNoDate} 00:00:00")
                val today = Calendar.getInstance()
                val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
                println("저장된 회차:${it.drwNo}")
                if (calDate > 7){
                    println("최신회차가 아님")
                    println("날짜차이:${(calDate / 7)}주지남}")
                    viewModel.searchLotto((it.drwNo + (calDate / 7).toInt()))
                    viewModel.lottoNum.observe(this,{ num ->
                        if (num != null){
                            viewModel.deleteLotto()
                            viewModel.insertLotto(LottoEntity(num.drwNo,num.drwNoDate))
                            startActivity(intent)
                            finish()
                        }
                    })

                } else {
                    println("최신회차")
                    startActivity(intent)
                    finish()
                }
            } else {
                println("앱이 처음 실행")
                println("저장된 회차가 없음")
                val sf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
                val date = sf.parse("2022-01-29 00:00:00")
                val today = Calendar.getInstance()
                val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
                println("날짜차이:${(calDate / 7)}주지남")

                viewModel.searchLotto(1000+(calDate / 7).toInt())
                viewModel.lottoNum.observe(this,{ num ->
                    if (num != null){
                        viewModel.insertLotto(LottoEntity(num.drwNo,num.drwNoDate))
                        startActivity(intent)
                        finish()
                    }
                })

            }
        })
    }


}