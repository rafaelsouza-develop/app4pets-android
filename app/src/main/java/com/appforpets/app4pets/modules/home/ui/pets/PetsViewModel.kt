package com.appforpets.app4pets.modules.home.ui.pets


import androidx.lifecycle.MutableLiveData
import com.appforpets.app4pets.modules.base.BaseViewModel
import javax.inject.Inject


class PetsViewModel @Inject constructor(): BaseViewModel() {

    val viewState = MutableLiveData<ViewState>()

    sealed class ViewState {


    }
}