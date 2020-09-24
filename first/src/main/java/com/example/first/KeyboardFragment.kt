package com.example.first

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class KeyboardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     //   return super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.keyboard, null)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }
}