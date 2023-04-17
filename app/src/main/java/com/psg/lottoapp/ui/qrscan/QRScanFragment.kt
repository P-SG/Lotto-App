package com.psg.lottoapp.ui.qrscan

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.psg.lottoapp.databinding.FragmentQrscanBinding

class QRScanFragment : Fragment() {

    private var _binding : FragmentQrscanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentQrscanBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initQRcodeScanner()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initQRcodeScanner()
    }

    private fun initQRcodeScanner(){
        val integrator = IntentIntegrator(requireActivity())
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
            Toast.makeText(requireContext(),"QR코드 인증이 취소되었습니다.",Toast.LENGTH_SHORT).show()
        }else {
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