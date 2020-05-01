package com.battagliandrea.covid19.ui.caselist

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
import com.battagliandrea.covid19.ui.models.caseitem.CasesAdapter
import com.battagliandrea.covid19.ui.common.ListItem
import com.battagliandrea.covid19.ui.utils.MarginItemDecorator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_case_list.*
import javax.inject.Inject


class CaseListFragment : Fragment() {

    private lateinit var mViewModel: CaseListViewModel

    @Inject
    lateinit var mAdapter: CasesAdapter

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

        mViewModel = getViewModel<CaseListViewModel>(viewModelFactory)
        with(mViewModel) {
            observe(casesList){ renderCasesList(it) }
        }

        setupList()
    }

    private fun setupList(){
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(MarginItemDecorator(resources.getDimension(R.dimen.default_padding).toInt(), 0))
    }

    private fun renderCasesList(data: List<ListItem>){
        mAdapter.updateList(data)
    }
}
