package com.appforpets.app4pets.modules.base


import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appforpets.app4pets.domain.exception.NoCredentialException
import com.appforpets.app4pets.domain.exception.NoInternetConnectionException
import com.appforpets.app4pets.domain.exception.NoUserLoggedException
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    protected val subscriptions = CompositeDisposable()

    sealed class ViewState {
        class ShowProgress(val show: Boolean): ViewState()
        class ShowErrorMessage(@StringRes var message: Int) : ViewState()
        class ForceLogout : ViewState()
        class ShowNoInternetConnectionDialog : ViewState()
        class ExpiredSession: ViewState()
    }

    val baseViewState = MutableLiveData<ViewState>()

    open fun catchExceptions(error: Throwable) {

        when (error){

            is NoInternetConnectionException -> {
                baseViewState.value = ViewState.ShowNoInternetConnectionDialog()
            }

            is NoUserLoggedException -> {
                baseViewState.value = ViewState.ForceLogout()
            }

            is NoCredentialException -> {
                baseViewState.value = ViewState.ForceLogout()
            }

            is HttpException ->{
                if(error.code() == 401){
                    baseViewState.value = ViewState.ExpiredSession()
                }
            }

            /*else -> {
                logHelper.logError(error)
            }*/
        }
    }


    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }
}