package com.nags.breweires.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nags.breweires.ui.BaseActivity
import com.nags.breweires.ui.BaseFragment
import com.nags.breweires.ui.details.BreweryDetailsFragment
import com.nags.breweires.ui.home.adapter.BreweriesAdapter
import com.nags.breweires.business.home.model.BreweryDomainModel
import com.nags.breweires.core.utils.AppUtils
import com.nags.breweries.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_progress_view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Home fragment which is responsible to display list of breweries.
 */
class HomeFragment : BaseFragment(), BreweriesAdapter.IItemCallback {

    /**
     * Injected view holder for this fragment.
     */
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val breweriesAdapter = BreweriesAdapter()
        breweriesAdapter.setItemClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = breweriesAdapter
        recyclerView.addItemDecoration(getDivider())

        homeViewModel.initCall(AppUtils.isNetworkConnected(context!!))

        homeViewModel.getBreweries().observe(this, Observer {
            breweriesAdapter.setData(it)
        })
        homeViewModel.onError().observe(this, Observer {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        })
        homeViewModel.getLoader().observe(this, Observer {
            displayLoaderView(it)
        })
    }

    /**
     * Callback method when a list item is selected.
     * @param brewery Model which is selected.
     */
    override fun onBrewerySelected(brewery: BreweryDomainModel) {
        (context as BaseActivity).addFragment(BreweryDetailsFragment.getInstance(brewery), null)
    }

    /**
     * Method responsible to display/hide loaders being used with API call.
     */
    private fun displayLoaderView(display: Boolean) {
        progressViewGroup.visibility = if (display) View.VISIBLE else View.GONE
    }

    /**
     * Item decorator is better choice to display divider in the list.
     */
    private fun getDivider(): DividerItemDecoration {
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.list_divider)!!)
        return itemDecorator
    }

    companion object {
        fun getInstance(): HomeFragment {
            val homeFragment = HomeFragment()
            homeFragment.arguments = Bundle()
            return homeFragment
        }
    }
}