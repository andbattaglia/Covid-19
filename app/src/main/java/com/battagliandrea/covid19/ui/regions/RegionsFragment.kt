package com.battagliandrea.covid19.ui.regions

import android.app.Dialog
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
import com.battagliandrea.covid19.ui.listdialog.ListDialog
import com.battagliandrea.covid19.ui.listdialog.ListDialogItem
import com.battagliandrea.covid19.ui.utils.MarginItemDecorator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_regions.*
import kotlinx.android.synthetic.main.fragment_regions.recyclerView
import javax.inject.Inject


class RegionsFragment : Fragment() {

    private lateinit var mViewModel: RegionsViewModel

    @Inject
    lateinit var mAdapter: RegionsDailyCasesAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_regions, container, false)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = getViewModel<RegionsViewModel>(viewModelFactory)
        with(mViewModel) {
            observe(regionsDialog){ renderRegionsDialog(it) }
            observe(regionsChooser){ renderRegionsChooser(it) }
            observe(listViewState){ renderCasesList(it) }
        }

        chooser.setOnClickListener { mViewModel.loadRegionsItems() }

        setupList()
    }

    private fun setupList(){
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(MarginItemDecorator(resources.getDimension(R.dimen.default_padding).toInt(), 1))
    }

    private fun renderRegionsDialog(viewState: RegionsViewState.RegionsDialog){
        with(viewState){

            val dialog = ListDialog.Builder()
                .items(regions.toMutableList())
                .itemClickListener(object: ListDialog.DialogListener{
                    override fun onClickListener(dialog: Dialog, item: ListDialogItem) {
                        mViewModel.selectRegion(code = item.id)
                        dialog.dismiss()
                    }
                })
                .build()

            dialog.show(childFragmentManager, "region_chooser_dialog");
        }
    }

    private fun renderRegionsChooser(viewState: RegionsViewState.RegionChooser){
        with(viewState){

            when(chooserViewState){
                is ViewState.Success ->  tvChooser.text = chooserViewState.data?.name.orEmpty()
                is ViewState.Error -> tvChooser.text = chooserViewState.throwable.message
                is ViewState.Loading -> tvChooser.text = ""
            }
        }
    }

    private fun renderCasesList(viewState: RegionsViewState.CasesList){
        with(viewState){
            when(listViewState){
                is ViewState.Success -> {
                    mAdapter.updateList(listViewState.data.orEmpty())
                }
                is ViewState.Error -> {
                    mAdapter.updateList(listViewState.data.orEmpty())
                }
            }
        }
    }

}
