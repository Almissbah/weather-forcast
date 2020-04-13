package com.almissbah.weather.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class SearchInputUtilsTest {
    private val query1 = "Dubai, Abu Dhabi"
    private val query2 = "Dubai, Abu Dhabi,"
    private val query3 = "Dubai, Abu Dhabi,Sharijah"
    private val query4 = "Dubai, Abu Dhabi,,,,,"
    private val query5 = "Dubai, Abu Dhabi,Dubai,Dubai,Dubai,Dubai,Dubai,Dubai"
    private val query6 = "Dubai, Abu Dhabi,City1,City2,City3,City4,City5,City6"

    @Test
    fun testValidateCitiesQuery() {
        assertEquals(
            SearchInputUtils.CitiesCount.LessThanThreeCities,
            SearchInputUtils.validateCitiesQuery(query1)
        )
        assertEquals(
            SearchInputUtils.CitiesCount.LessThanThreeCities,
            SearchInputUtils.validateCitiesQuery(query2)
        )
        assertEquals(
            SearchInputUtils.CitiesCount.Valid,
            SearchInputUtils.validateCitiesQuery(query3)
        )

        assertEquals(
            SearchInputUtils.CitiesCount.LessThanThreeCities,
            SearchInputUtils.validateCitiesQuery(query4)
        )


        assertEquals(
            SearchInputUtils.CitiesCount.LessThanThreeCities,
            SearchInputUtils.validateCitiesQuery(query5)
        )

        assertEquals(
            SearchInputUtils.CitiesCount.MoreThanSevenCities,
            SearchInputUtils.validateCitiesQuery(query6)
        )

    }

    @Test
    fun testValidateCitiesCount() {
        assertEquals(
            SearchInputUtils.CitiesCount.LessThanThreeCities,
            SearchInputUtils.validateCitiesCount(1)
        )
        assertEquals(
            SearchInputUtils.CitiesCount.LessThanThreeCities,
            SearchInputUtils.validateCitiesCount(2)
        )
        assertEquals(
            SearchInputUtils.CitiesCount.Valid,
            SearchInputUtils.validateCitiesCount(3)
        )
        assertEquals(
            SearchInputUtils.CitiesCount.Valid,
            SearchInputUtils.validateCitiesCount(4)
        )
        assertEquals(
            SearchInputUtils.CitiesCount.Valid,
            SearchInputUtils.validateCitiesCount(5)
        )
        assertEquals(
            SearchInputUtils.CitiesCount.Valid,
            SearchInputUtils.validateCitiesCount(6)
        )
        assertEquals(
            SearchInputUtils.CitiesCount.Valid,
            SearchInputUtils.validateCitiesCount(7)
        )
        assertEquals(
            SearchInputUtils.CitiesCount.MoreThanSevenCities,
            SearchInputUtils.validateCitiesCount(8)
        )

        assertEquals(
            SearchInputUtils.CitiesCount.MoreThanSevenCities,
            SearchInputUtils.validateCitiesCount(80)
        )
    }


    @Test
    fun testGetValidateCityList() {
        assertEquals(
            2,
            SearchInputUtils.getValidCityList(query4).size
        )
        assertEquals(
            "Dubai",
            SearchInputUtils.getValidCityList(query4)[0]
        )

        assertEquals(
            "Abu Dhabi",
            SearchInputUtils.getValidCityList(query4)[1]
        )

    }
}