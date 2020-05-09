package com.appforpets.app4pets.modules.home

import androidx.lifecycle.MutableLiveData
import com.appforpets.app4pets.helpers.session.UserSessionHelper
import com.appforpets.app4pets.modules.base.BaseViewModel
import javax.inject.Inject

class CoreViewModel @Inject constructor(val userSessionHelper: UserSessionHelper) : BaseViewModel() {


    val viewState = MutableLiveData<ViewState>()

    sealed class ViewState {
        class VerifyIsLogged(val isLogged: Boolean) : ViewState()
        class Logout: ViewState()

    }

    fun verifyIsLogger(){
       viewState.value = ViewState.VerifyIsLogged(userSessionHelper.hasUserLogged())
    }

    fun logout(){
        userSessionHelper.logout()
        viewState.value = ViewState.Logout()
    }
}