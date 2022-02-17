package com.psg.lottoapp.view.qrscan

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.psg.lottoapp.R
import com.psg.lottoapp.databinding.ActivityQrscanBinding
import com.psg.lottoapp.view.base.BaseActivity
import com.psg.lottoapp.view.generate.GeneNumActivity
import com.psg.lottoapp.view.generate.GeneNumViewModel
import org.koin.android.ext.android.inject

class QRScanActivity : BaseActivity<ActivityQrscanBinding,QRScanViewModel>(R.layout.activity_qrscan) {
    override val TAG: String = QRScanActivity::class.java.simpleName
    override val viewModel: QRScanViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initQRcodeScanner()
    }

    private fun initQRcodeScanner(){
        val integrator = IntentIntegrator(this)
        integrator.setBeepEnabled(false)
        integrator.setOrientationLocked(false)
        integrator.setBarcodeImageEnabled(true)
//        integrator.captureActivity = QRScanActivity::class.java
        integrator.setPrompt("QR코드를 스캔해주세요.")
        integrator.initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result.contents == null){
            Toast.makeText(this,"QR코드 인증이 취소되었습니다.",Toast.LENGTH_SHORT).show()
            finish()
        }else {
            println("결과는?:${result.contents}")
            val url = result.contents
            loadWebView(url)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadWebView(url:String){
        binding.wvLotto.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }
        binding.wvLotto.loadUrl(url)

    }


}