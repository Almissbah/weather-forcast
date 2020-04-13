package com.almissbah.weather.utils


class SearchInputUtils {
    enum class CitiesCount { Valid, LessThanThreeCities, MoreThanSevenCities }

    companion object {

        fun getValidCityList(query: String): List<String> {
            return query.split(",").filter { it.isNotBlank() }.map {
                it.trim()
            }.toSet().toList()
        }

        fun validateCitiesQuery(query: String): CitiesCount {
            val list = getValidCityList(query)
            return validateCitiesCount(list)
        }

        fun validateCitiesCount(list: List<String>): CitiesCount {
            return when {
                list.size < 3 -> {
                    CitiesCount.LessThanThreeCities
                }
                list.size > 7 -> CitiesCount.MoreThanSevenCities
                else -> CitiesCount.Valid
            }
        }
    }
}