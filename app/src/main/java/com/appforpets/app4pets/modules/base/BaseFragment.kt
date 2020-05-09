package com.appforpets.app4pets.modules.base

import android.content.ContextWrapper
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    fun getBaseActivity(): BaseActivity? {
        var context = context
        while (context is ContextWrapper) {
            if (context is BaseActivity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    fun setViewModel(baseViewModel: BaseViewModel) {

        if (activity != null && activity is BaseActivity) {
            (activity as BaseActivity).setViewModel(baseViewModel)
        }
    }
}