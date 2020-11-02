package com.example.second.util

import android.text.InputFilter
import android.text.Spanned
import java.lang.NumberFormatException

class InputFilterMinMax(min:String, max:String) : InputFilter {
    private var min:Float = 0.0F
    private var max:Float = 0.0F
    init {
        this.min = min.toFloat()
        this.max = max.toFloat()
    }

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try{
            val input = (dest.subSequence(0,dstart).toString() + source + dest.subSequence(dend,
                dest.length
            )).toFloat()
            if(isInRange(min, max, input))
                return null
        }
        catch(nfe:NumberFormatException){}
        return ""
    }

    private fun isInRange(a:Float, b:Float, c:Float):Boolean{
        return if(b > a) c in a..b else c in b..a
    }

}