package com.appforpets.app4pets.modules.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.appforpets.app4pets.App4petsApplication.Companion.context
import com.appforpets.app4pets.R
import kotlinx.android.synthetic.main.alert_custom_layout.view.*


open class BaseActivity: AppCompatActivity() {

    var alertDialog:AlertDialog? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun setViewModel(viewModel: BaseViewModel){

        viewModel.baseViewState.observe(this, Observer {
            updateViewState(it)
        })
    }

    private fun updateViewState(state: BaseViewModel.ViewState?) {
        when (state) {
            is BaseViewModel.ViewState.ShowProgress -> showProgressDialog(state.show)
            //is BaseViewModel.ViewState.ShowErrorMessage -> showErrorMessage(state.message)
            //is BaseViewModel.ViewState.ExpiredSession -> onForceLogoffUser()
        }
    }

    private fun showProgressDialog(show: Boolean) {
        if (show) {
            showProgressDialog()
        }else {
            dismissProgressDialog()
        }
    }

    private fun showProgressDialog() {
        alertDialog?.dismiss()
        val layoutBuilder = LayoutInflater.from(context).inflate(R.layout.alert_custom_layout, null)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(layoutBuilder)
         alertDialog  = builder.show()

        //layoutBuilder.tv_alert.text = alertText
        layoutBuilder.lottie_anim.setAnimation(R.raw.paws_loading)


        layoutBuilder.lottie_anim.loop(true)
        layoutBuilder.lottie_anim.playAnimation()

    }

    fun dismissProgressDialog() {
        alertDialog?.dismiss()
    }
}