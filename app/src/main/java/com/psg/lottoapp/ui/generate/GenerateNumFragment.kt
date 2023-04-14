package com.psg.lottoapp.ui.generate

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.psg.lottoapp.R
import com.psg.lottoapp.databinding.ActivityGeneNumBinding
import com.psg.lottoapp.databinding.FragmentGenerateNumBinding
import com.psg.lottoapp.ui.base.BaseFragment

class GenerateNumFragment : BaseFragment<FragmentGenerateNumBinding, GenerateNumViewModel>() {

    override val viewModel: GenerateNumViewModel by viewModels()
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGenerateNumBinding = FragmentGenerateNumBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        initView()
    }

    private fun initView(){
        viewModel.lottoNum.observe(this) {
            if (it != null) {
                binding.tvLottoNum1.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[1]!!)))
                binding.tvLottoNum2.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[2]!!)))
                binding.tvLottoNum3.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[3]!!)))
                binding.tvLottoNum4.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[4]!!)))
                binding.tvLottoNum5.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[5]!!)))
                binding.tvLottoNum6.backgroundTintList =
                    ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[6]!!)))
            }

        }
    }



}