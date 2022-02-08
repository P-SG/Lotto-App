package com.psg.lottoapp.view.generate

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
    }
}