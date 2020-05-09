package com.appforpets.app4pets.modules.register

import androidx.lifecycle.MutableLiveData
import com.appforpets.app4pets.domain.RegisterUseCase
import com.appforpets.app4pets.modules.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegisterViewModel @Inject constructor(val registerUseCase: RegisterUseCase): BaseViewModel() {

    val viewState = MutableLiveData<ViewState>()

    sealed class ViewState {
        class RegisterSuccess(val isRegister: Boolean): ViewState()

    }

    fun register (email:String,name:String, password: String){

        baseViewState.value = BaseViewModel.ViewState.ShowProgress(true)

        var disposable = registerUseCase.createAccount(email, name, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onAuthLoginSuccess, this::onAuthLoginError)


        subscriptions.add(disposable)

    }

    private fun onAuthLoginSuccess(isRegister: Boolean){
        baseViewState.value = BaseViewModel.ViewState.ShowProgress(false)
        viewState.value = ViewState.RegisterSuccess(isRegister)

    }

    private fun onAuthLoginError(error:Throwable){
        baseViewState.value = BaseViewModel.ViewState.ShowProgress(false)

    }
}