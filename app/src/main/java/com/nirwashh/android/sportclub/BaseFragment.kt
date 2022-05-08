package com.nirwashh.android.sportclub

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

abstract class BaseFragment: Fragment() {
    protected lateinit var fm: FragmentManager
    override fun onAttach(context: Context) {
        super.onAttach(context)
        fm = (context as FragmentActivity).supportFragmentManager
    }


}