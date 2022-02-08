package com.psg.lottoapp.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.psg.lottoapp.R
import com.psg.lottoapp.data.model.LottoEntity
import com.psg.lottoapp.databinding.ActivitySplashBinding
import com.psg.lottoapp.view.base.BaseActivity
import com.psg.lottoapp.view.main.MainActivity
import com.psg.lottoapp.view.main.MainViewModel
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class SplashActivity : BaseActivity<ActivitySplashBinding,SplashViewModel>(R.layout.activity_splash) {
    override val TAG: String = MainActivity::class.java.simpleName
    override val viewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNum()
    }

    private fun checkNum(){
        val intent = Intent(this,MainActivity::class.java)

        viewModel.lottoEntity.observe(this,{
            if (it != null){
                val sf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
                val date = sf.parse("${it.drwNoDate} 00:00:00")
                val today = Calendar.getInstance()
                val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
                if (calDate > 7){
                    startActivity(intent)
                } else {
                    startActivity(intent)
                }
                println()
            } else {
                startActivity(intent)
            }
        })
    }

//    private fun checkDate(date: Date){
//        val sf = SimpleDateFormat("yyyy-MM-dd 00:00:00")
//        val today = Calendar.getInstance()
//        val calDate = (today.time.time - date.time) / (60 * 60 * 24 * 1000)
//        if (calDate > 7){
//            viewModel.deleteLotto()
//            viewModel.insertLotto(LottoEntity())
//            startActivity(intent)
//        } else {
//            startActivity(intent)
//        }
//    }
}