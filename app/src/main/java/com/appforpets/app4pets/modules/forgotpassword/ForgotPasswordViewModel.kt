package com.appforpets.app4pets.modules.forgotpassword

import androidx.lifecycle.MutableLiveData
import com.appforpets.app4pets.modules.base.BaseViewModel
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(): BaseViewModel() {

    val viewState = MutableLiveData<ViewState>()

    sealed class ViewState {


    }
}