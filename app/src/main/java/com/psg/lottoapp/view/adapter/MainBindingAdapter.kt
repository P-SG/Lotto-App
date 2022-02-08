package com.psg.lottoapp.view.adapter

import android.content.res.ColorStateList
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("backcolor")
fun numColor(view: TextView, res: Int){
    view.backgroundTintList = ColorStateList.valueOf(res)
}