package com.almissbah.weather.utils

import java.util.*


class SearchInputUtils {
    enum class CitiesCount { Valid, LessThanThreeCities, MoreThanSevenCities }

    companion object {

        fun getValidCityList(query: String): List<String> {
            return query.split(",").filter { it.isNotBlank() }.map {
                it.trim().toLowerCase(Locale.ROOT)
            }.toSet().toList()
        }

        fun validateCitiesQuery(query: String): CitiesCount {
            val list = getValidCityList(query)
            return validateCitiesCount(list.size)
        }

        fun validateCitiesCount(size: Int): CitiesCount {
            return when {
                size < 3 -> {
                    CitiesCount.LessThanThreeCities
                }
                size > 7 -> CitiesCount.MoreThanSevenCities
                else -> CitiesCount.Valid
            }
        }
    }
}