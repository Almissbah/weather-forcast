package com.almissbah.weather.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.almissbah.weather.R
import com.almissbah.weather.ui.base.WeatherForecastFragment
import com.almissbah.weather.ui.search.adapter.SearchResultAdapter
import com.almissbah.weather.utils.SearchInputUtils
import com.almissbah.weather.utils.hide
import com.almissbah.weather.utils.unHide
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : WeatherForecastFragment() {

    private var mAdapter: SearchResultAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var searchViewModel: SearchViewModel

    override fun initViewModel() {
        searchViewModel =
            ViewModelProviders.of(
                this,
                viewModelFactory
            )[SearchViewModel::class.java]
    }

    override fun initViews() {
        rvSearchResults.layoutManager = LinearLayoutManager(this.context)
        mAdapter = SearchResultAdapter()
        rvSearchResults.adapter = mAdapter
        btnSearch.setOnClickListener {
            searchViewModel.validateInput(etSearch.text.toString())

        }
        mProgressBar = progressBar
    }

    override fun subscribe() {
        searchViewModel.queryValidator.observe(viewLifecycleOwner, Observer {
            when (it) {
                SearchInputUtils.CitiesCount.LessThanThreeCities -> etSearch.error =
                    "You entered less than three different cities !"
                SearchInputUtils.CitiesCount.MoreThanSevenCities -> etSearch.error =
                    "You entered more than seven cities !"
                SearchInputUtils.CitiesCount.Valid -> {
                    btnSearch.hide()
                    showLoading()
                }
            }
        })



        searchViewModel.searchResult.observe(viewLifecycleOwner, Observer {
            btnSearch.unHide()
            hideLoading()
            when (it.action) {
                SearchViewModel.Action.UpdateList -> updateList(it.payload)
                SearchViewModel.Action.ShowNetworkError -> showNetworkError()
            }
        })
    }

    private fun showNetworkError() {
        Log.i("showNetworkError", "showNetworkError")
        showSnackbar(view!!, "Failed to connect !")
    }

    private fun updateList(payload: MutableList<CityWeatherWithData>?) {
        mAdapter?.setData(payload!!)
    }

    override fun unSubscribe() {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        return root
    }


}
