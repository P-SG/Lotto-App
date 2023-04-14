package com.psg.lottoapp.ui.qrscan

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.psg.lottoapp.R
import com.psg.lottoapp.databinding.ActivityQrscanBinding
import com.psg.lottoapp.databinding.FragmentQrscanBinding
import com.psg.lottoapp.ui.base.BaseFragment

class QRScanFragment : BaseFragment<FragmentQrscanBinding, QRScanViewModel>() {
    override val viewModel: QRScanViewModel by viewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQrscanBinding = FragmentQrscanBinding.inflate(inflater, container, false)

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
            AppLogger.println("결과는?:${result.contents}")
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