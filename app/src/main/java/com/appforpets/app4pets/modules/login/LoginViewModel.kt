package com.appforpets.app4pets.modules.login

import androidx.lifecycle.MutableLiveData
import com.appforpets.app4pets.data.remote.models.LoginResponse
import com.appforpets.app4pets.domain.LoginUserCase
import com.appforpets.app4pets.domain.TokenUseCase
import com.appforpets.app4pets.models.User
import com.appforpets.app4pets.modules.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(
                                private val loginUserCase: LoginUserCase): BaseViewModel() {

    val viewState = MutableLiveData<ViewState>()

    sealed class ViewState {
        class LoginSuccess(val user: User) : ViewState()

    }

    fun login (email:String, password: String){

        baseViewState.value = BaseViewModel.ViewState.ShowProgress(true)

        var disposable = loginUserCase.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onAuthLoginSuccess, this::onAuthLoginError)


        subscriptions.add(disposable)

    }

    private fun onAuthLoginSuccess(user: User){
        baseViewState.value = BaseViewModel.ViewState.ShowProgress(false)


        viewState.value = ViewState.LoginSuccess(user)

    }

    private fun onAuthLoginError(error:Throwable){
        baseViewState.value = BaseViewModel.ViewState.ShowProgress(false)

    }

}