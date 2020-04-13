package com.almissbah.weather.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.almissbah.weather.R
import com.almissbah.weather.ui.base.WeatherForecastFragment
import com.almissbah.weather.utils.SearchInputUtils
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : WeatherForecastFragment() {

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
        btnSearch.setOnClickListener {
            searchViewModel.validateInput(etSearch.text.toString())
        }
    }

    override fun subscribe() {
        searchViewModel.queryValidator.observe(viewLifecycleOwner, Observer {
            when (it) {
                SearchInputUtils.CitiesCount.LessThanThreeCities -> etSearch.error =
                    "You entered less than three different cities !"
                SearchInputUtils.CitiesCount.MoreThanSevenCities -> etSearch.error =
                    "You entered more than seven cities !"
            }
        })
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
