package com.battagliandrea.covid19.ui.dailycases

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.battagliandrea.covid19.R
import com.battagliandrea.covid19.ext.getViewModel
import com.battagliandrea.covid19.ext.observe
import com.battagliandrea.covid19.ui.base.ViewState
import com.battagliandrea.covid19.ui.utils.MarginItemDecorator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_case_list.*
import kotlinx.android.synthetic.main.view_error.*
import javax.inject.Inject


class DailyCasesFragment : Fragment() {

    private lateinit var mViewModel: DailyCasesViewModel

    @Inject
    lateinit var mAdapterDaily: DailyCasesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_case_list, container, false)
        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = getViewModel<DailyCasesViewModel>(viewModelFactory)
        with(mViewModel) {
            observe(headerViewState){ renderHeader(it) }
            observe(listViewState){ renderCasesList(it) }
        }

        setupList()
    }

    private fun setupList(){
        recyclerView.adapter = mAdapterDaily
        recyclerView.addItemDecoration(MarginItemDecorator(resources.getDimension(R.dimen.default_padding).toInt(), 0))
    }

    private fun renderHeader(viewState: DailyCasesViewState.Header){
        with(viewState){
            tvTitle.text = title
            tvDescription.text = description
        }
    }

    private fun renderCasesList(viewState: DailyCasesViewState.CasesList){
        with(viewState){
            when(listViewState){
                is ViewState.Success -> {
                    containerError.visibility = View.GONE
                    mAdapterDaily.updateList(listViewState.data.orEmpty())
                }
                is ViewState.Error -> {
                    containerError.visibility = View.VISIBLE
                    tvError.text = listViewState.throwable.message
                    mAdapterDaily.updateList(listViewState.data.orEmpty())
                }
                is ViewState.Loading -> {
                    containerError.visibility = View.GONE
                    mAdapterDaily.updateList(listViewState.data.orEmpty())
                }
            }
        }
    }
}
