package com.psg.lottoapp.util

import android.util.Log

class AppLogger {
    companion object{
        fun d(tag:String,msg:String){
            if (Constants.DEBUG){
                Log.d(tag, msg)
            }
        }

        fun i(tag:String,msg:String){
            if (Constants.DEBUG){
                Log.i(tag, msg)
            }
        }

        fun println(msg:String){
            if (Constants.DEBUG){
                AppLogger.println(msg)
            }
        }

    }
}