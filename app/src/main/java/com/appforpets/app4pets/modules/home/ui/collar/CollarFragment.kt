package com.appforpets.app4pets.modules.home.ui.collar

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
import javax.inject.Inject


class CollarFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CollarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_collar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App4petsApplication.daggerComponent.inject(this)
        viewModel = getViewModel()
        setViewModel(viewModel)
        setupObserverViewState(viewModel)
    }

    private fun getViewModel(): CollarViewModel {

        return ViewModelProviders
            .of(this, viewModelFactory)
            .get(CollarViewModel::class.java)
    }

    private fun setupObserverViewState(viewModel: CollarViewModel) {
        viewModel.viewState.observe(this, Observer {
            updateView(it)
        })
    }

    private fun updateView(viewState: CollarViewModel.ViewState?) {

        when(viewState) {
            //is AccountViewModel.ViewState.FillOutView -> fillOutView(viewState.filters)
            // is AccountViewModel.ViewState.FillOutViewFilters -> getFilterSelected(viewState.filters)

        }
    }


}