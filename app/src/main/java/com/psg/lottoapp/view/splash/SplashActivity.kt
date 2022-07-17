package com.psg.lottoapp.view.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.psg.lottoapp.R
import com.psg.domain.model.LottoDate
import com.psg.lottoapp.databinding.ActivitySplashBinding
import com.psg.lottoapp.util.AppLogger
import com.psg.lottoapp.view.base.BaseActivity
import com.psg.lottoapp.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>(R.layout.activity_splash) {
    override val TAG: String = MainActivity::class.java.simpleName
    override val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNewDrwNo()
    }

    private fun checkNewDrwNo(){
        viewModel.getLocalLotto()
        val intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP

        viewModel.lottoDate.observe(this) {
            if (it != null) {
                val sf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
                val date = sf.parse("${it.drwNoDate} 00:00:00")
                val today = Calendar.getInstance()
                val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
                AppLogger.println("저장된 회차:${it.drwNo}")
                if (calDate > 7) {
                    AppLogger.println("최신회차가 아님")
                    AppLogger.println("날짜차이:${(calDate / 7)}주지남}")
                    viewModel.getRemoteLotto((it.drwNo + (calDate / 7).toInt()))
                    viewModel.lottoNum.observe(this) { num ->
                        if (num != null) {
                            viewModel.deleteLotto()
                            viewModel.insertLotto(
                                LottoDate(
                                    num.drwNo,
                                    num.drwNoDate
                                )
                            )
                            startActivity(intent)
                            finish()
                        }
                    }

                } else {
                    AppLogger.println("최신회차")
                    startActivity(intent)
                    finish()
                }
            } else {
                AppLogger.println("앱이 처음 실행")
                AppLogger.println("저장된 회차가 없음")
                val sf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
                val date = sf.parse("2022-01-29 00:00:00")
                val today = Calendar.getInstance()
                val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
                AppLogger.println("날짜차이:${(calDate / 7)}주지남")

                viewModel.getRemoteLotto(1000 + (calDate / 7).toInt())
                viewModel.lottoNum.observe(this) { num ->
                    if (num != null) {
                        viewModel.insertLotto(
                            LottoDate(
                                num.drwNo,
                                num.drwNoDate
                            )
                        )
                        startActivity(intent)
                        finish()
                    }
                }

            }
        }
    }


}