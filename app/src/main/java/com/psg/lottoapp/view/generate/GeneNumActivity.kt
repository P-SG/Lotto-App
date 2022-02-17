package com.psg.lottoapp.view.generate

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.psg.lottoapp.R
import com.psg.lottoapp.databinding.ActivityGeneNumBinding
import com.psg.lottoapp.view.base.BaseActivity
import org.koin.android.ext.android.inject

class GeneNumActivity : BaseActivity<ActivityGeneNumBinding,GeneNumViewModel>(R.layout.activity_gene_num) {
    override val TAG: String = GeneNumActivity::class.java.simpleName
    override val viewModel: GeneNumViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = viewModel
        initView()
    }

    private fun initView(){
        viewModel.lottoNum.observe(this,{
            if (it != null){
                binding.tvLottoNum1.backgroundTintList = ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[1]!!)))
                binding.tvLottoNum2.backgroundTintList = ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[2]!!)))
                binding.tvLottoNum3.backgroundTintList = ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[3]!!)))
                binding.tvLottoNum4.backgroundTintList = ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[4]!!)))
                binding.tvLottoNum5.backgroundTintList = ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[5]!!)))
                binding.tvLottoNum6.backgroundTintList = ColorStateList.valueOf(resources.getColor(viewModel.numColor(it[6]!!)))
            }

        })
    }



}