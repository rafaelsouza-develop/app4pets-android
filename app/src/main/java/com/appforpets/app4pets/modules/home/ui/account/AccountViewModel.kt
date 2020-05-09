package com.appforpets.app4pets.modules.home.ui.account


import androidx.lifecycle.MutableLiveData
import com.appforpets.app4pets.helpers.session.UserSessionHelper
import com.appforpets.app4pets.modules.base.BaseViewModel
import javax.inject.Inject


class AccountViewModel @Inject constructor(val userSessionHelper: UserSessionHelper): BaseViewModel() {

    val viewState = MutableLiveData<ViewState>()

    sealed class ViewState {
        class Logout: AccountViewModel.ViewState()

    }

    fun logout(){
        userSessionHelper.logout()
        viewState.value = ViewState.Logout()
    }
}