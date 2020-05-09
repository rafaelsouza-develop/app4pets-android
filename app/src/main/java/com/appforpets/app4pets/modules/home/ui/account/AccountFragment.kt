package com.appforpets.app4pets.modules.home.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.appforpets.app4pets.App4petsApplication
import com.appforpets.app4pets.R
import com.appforpets.app4pets.modules.base.BaseFragment
import com.appforpets.app4pets.modules.home.CoreActivity
import kotlinx.android.synthetic.main.fragment_account.*
import javax.inject.Inject


class AccountFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AccountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App4petsApplication.daggerComponent.inject(this)
        viewModel = getViewModel()
        setViewModel(viewModel)
        setupObserverViewState(viewModel)
        btnLogout.setOnClickListener { viewModel.logout() }
    }

    private fun getViewModel(): AccountViewModel {

        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(AccountViewModel::class.java)
    }

    private fun setupObserverViewState(viewModel: AccountViewModel) {
        viewModel.viewState.observe(this, Observer {
            updateView(it)
        })
    }

    private fun updateView(viewState: AccountViewModel.ViewState?) {

        when(viewState) {
            is AccountViewModel.ViewState.Logout -> logout()
           // is AccountViewModel.ViewState.FillOutViewFilters -> getFilterSelected(viewState.filters)

        }
    }

    private fun logout() {
        (context as CoreActivity).finish()
        (context as CoreActivity).callLoginScreen()

    }
}