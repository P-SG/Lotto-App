package com.psg.lottoapp.view.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.psg.lottoapp.util.AppLogger

abstract class BaseActivity<T: ViewDataBinding, V: BaseViewModel>(@LayoutRes val res: Int): AppCompatActivity() {
    lateinit var binding: T
    abstract val TAG: String
    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,res)
    }

    override fun onStart() {
        super.onStart()
        AppLogger.i(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        AppLogger.i(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        AppLogger.i(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        AppLogger.i(TAG,"onStop")
//        Log.i(TAG,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        AppLogger.i(TAG,"onDestroy")
    }

}