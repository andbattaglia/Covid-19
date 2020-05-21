package com.battagliandrea.covid19.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.battagliandrea.covid19.R
import com.battagliandrea.covid19.di.viewmodel.InjectingSavedStateViewModelFactory
import com.battagliandrea.covid19.ext.getViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject


class MainFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel

    @Inject
    lateinit var abstractFactory: InjectingSavedStateViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = getViewModel<MainViewModel>(abstractFactory, savedInstanceState)
        detailsButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_regionsFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.loadDpcDailyVariation()
    }
}
