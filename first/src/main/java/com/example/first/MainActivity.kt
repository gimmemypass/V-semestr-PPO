package com.example.first

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.main.*

class MainActivity : AppCompatActivity(), KeyboardFragment.OnFunctionSelectedListener {
    private var keyFragment: KeyboardFragment? = null
    private var textFragment : TextFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        keyFragment = supportFragmentManager.findFragmentByTag("keyboard") as KeyboardFragment?
        textFragment = supportFragmentManager.findFragmentByTag("text") as TextFragment?
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun OnSwapSelected() {
        textFragment?.swap()
    }

    override fun OnTranslateSelected() {
        textFragment?.translate()
    }

}



