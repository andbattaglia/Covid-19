package com.battagliandrea.covid19.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.battagliandrea.covid19.R
import com.battagliandrea.covid19.ext.getViewModel
import com.battagliandrea.covid19.ext.observe
import com.battagliandrea.covid19.ui.caseitem.CasesAdapter
import com.battagliandrea.covid19.ui.common.ListItem
import com.battagliandrea.covid19.ui.utils.MarginItemDecorator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel

    @Inject
    lateinit var mAdapter: CasesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        exitTransition = Hold()
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = getViewModel<MainViewModel>(viewModelFactory)
        with(mViewModel) {
            observe(casesList){ renderBeers(it) }
        }

        setupList()
    }

    private fun setupList(){
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(MarginItemDecorator(resources.getDimension(R.dimen.default_padding).toInt(), 0))
    }

    private fun renderBeers(data: List<ListItem>){
        mAdapter.updateList(data)
    }
}
