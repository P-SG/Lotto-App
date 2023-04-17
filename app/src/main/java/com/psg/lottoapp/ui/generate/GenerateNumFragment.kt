package com.psg.lottoapp.ui.generate

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.psg.lottoapp.databinding.FragmentGenerateNumBinding
import com.psg.lottoapp.ui.base.BaseFragment
import com.psg.lottoapp.utils.toColor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenerateNumFragment : BaseFragment<FragmentGenerateNumBinding>() {

    private val viewModel: GenerateNumViewModel by viewModel()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGenerateNumBinding = FragmentGenerateNumBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    when (it) {
                        is GenerateNumState.Loading -> {}
                        is GenerateNumState.Error -> {}
                        is GenerateNumState.Success -> {
                            with (binding) {
                                tvLottoNum1.run {
                                    text = it.data[1].toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data[1].toColor()))
                                }
                                tvLottoNum2.run {
                                    text = it.data[2].toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data[2].toColor()))
                                }
                                tvLottoNum3.run {
                                    text = it.data[3].toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data[3].toColor()))
                                }
                                tvLottoNum4.run {
                                    text = it.data[4].toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data[4].toColor()))
                                }
                                tvLottoNum5.run {
                                    text = it.data[5].toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data[5].toColor()))
                                }
                                tvLottoNum6.run {
                                    text = it.data[6].toString()
                                    backgroundTintList = ColorStateList.valueOf(resources.getColor(it.data[6].toColor()))
                                }
                            }

                        }
                    }
                }
            }
        }
        binding.btnGenerateNum.setOnClickListener {
            viewModel.generateLotto()
        }

    }


}