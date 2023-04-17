package com.psg.lottoapp.ui.main

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.psg.lottoapp.R
import com.psg.lottoapp.databinding.FragmentLottoBinding
import com.psg.lottoapp.ui.base.BaseFragment
import com.psg.lottoapp.utils.toColor
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LottoFragment : BaseFragment<FragmentLottoBinding>() {
    private val viewModel: LottoViewModel by viewModel()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLottoBinding = FragmentLottoBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is LottoState.Loading -> {}
                        is LottoState.Error -> {}
                        is LottoState.Success -> {
                            with (binding) {
                                tvLottoNum1.run {
                                    text = it.data?.drwtNo1.toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data?.drwtNo1.toColor()))
                                }
                                tvLottoNum2.run {
                                    text = it.data?.drwtNo2.toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data?.drwtNo2.toColor()))
                                }
                                tvLottoNum3.run {
                                    text = it.data?.drwtNo3.toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data?.drwtNo3.toColor()))
                                }
                                tvLottoNum4.run {
                                    text = it.data?.drwtNo4.toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data?.drwtNo4.toColor()))
                                }
                                tvLottoNum5.run {
                                    text = it.data?.drwtNo5.toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data?.drwtNo5.toColor()))
                                }
                                tvLottoNum6.run {
                                    text = it.data?.drwtNo6.toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data?.drwtNo6.toColor()))
                                }
                                tvLottoBns.run {
                                    text = it.data?.bnusNo.toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data?.bnusNo.toColor()))
                                }
                            }
                        }
                    }
                }
            }
        }

        binding.etLottoNum.addTextChangedListener(textWatcher)

        binding.btnQrScan.setOnClickListener {
            findNavController().navigate(R.id.action_lottoFragment_to_QRScanFragment)
        }

        binding.btnGenerateNum.setOnClickListener {
            findNavController().navigate(R.id.action_lottoFragment_to_generateNumFragment)
        }

    }


    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            if (s!!.length > 1){
                viewModel.getLotto(s.toString().toInt())
            }
        }
    }

}