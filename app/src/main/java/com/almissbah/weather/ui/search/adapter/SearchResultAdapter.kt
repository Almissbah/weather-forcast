package com.almissbah.weather.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.almissbah.weather.R
import com.almissbah.weather.ui.search.SearchResult
import com.almissbah.weather.utils.gone
import com.almissbah.weather.utils.unHide


class SearchResultAdapter :
    RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    private var mList: MutableList<SearchResult> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = viewGroup.context
        val layoutInflater = LayoutInflater.from(context)

        val listItem: View = layoutInflater.inflate(R.layout.search_list_item, viewGroup, false)
        return ViewHolder(listItem)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            val searchResult = payloads[0] as SearchResult
            updateItemView(holder, position, searchResult)
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result: SearchResult = mList[holder.adapterPosition]
        holder.bindResult(result)
    }


    fun setData(newList: MutableList<SearchResult>) {

        val diffCallback =
            DiffUtilsCallback(
                mList,
                newList
            )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        mList = newList

    }

    private fun updateItemView(holder: ViewHolder, position: Int, result: SearchResult) {
        mList[position] = result

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var tvCityName: TextView = view.findViewById(R.id.tvCityName)
        var tvTemp: TextView = view.findViewById(R.id.tvTemp)
        var tvWindSpeed: TextView = view.findViewById(R.id.tvWindSpeed)
        var tvWeatherDescription: TextView = view.findViewById(R.id.tvWeatherDescription)
        var tvCityNotFound: TextView = view.findViewById(R.id.tvCityNotFound)
        var layoutCityData: ConstraintLayout = view.findViewById(R.id.layoutCityData)
        fun bindResult(result: SearchResult) {
            if (result.result == SearchResult.Result.Found) {
                tvCityNotFound.gone()
                tvCityName.text = result.cityName
                val mainInfo = result.cityWeather?.mainInfo
                tvTemp.text =
                    "${mainInfo?.minTemp} - ${mainInfo?.maxTemp}"
                tvWindSpeed.text =
                    "${result.cityWeather?.windInfo?.speed}"
                var weatherString = ""
                result.cityWeather?.weatherDescription?.forEach { weatherString += "${it.description} \n" }
                tvWeatherDescription.text = weatherString
            } else {
                tvCityName.text = result.cityName
                layoutCityData.gone()
                tvCityNotFound.unHide()
            }

        }
    }

}