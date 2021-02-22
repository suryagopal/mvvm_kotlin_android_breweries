package com.nags.breweires.business.home

import com.nags.breweires.business.common.dto.Brewery
import com.nags.breweires.business.home.repository.HomeLocalRepository
import com.nags.breweires.business.home.repository.HomeRepository
import com.nags.breweires.db.BreweryEntity
import io.reactivex.Single
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import java.lang.Exception

@RunWith(JUnit4::class)
class HomeUseCaseTest {

    private lateinit var mUseCase: HomeUseCase

    var mHomeRepository: HomeRepository = mock(HomeRepository::class.java)
    var mHomeLocalRepository: HomeLocalRepository = mock(HomeLocalRepository::class.java)

    @Before
    fun setup() {
        mUseCase = HomeUseCase(mHomeRepository, mHomeLocalRepository)
    }

    @Test
    fun getBreweriesData_whenConnectionAvailable_shouldSaveDataInDatabase() {
        val list = mutableListOf<Brewery>()
        `when`(mHomeRepository.getBreweries()).thenReturn(Single.just(list))

        mUseCase.getBreweriesData(true).test().assertComplete()

        verify(mHomeRepository).getBreweries()
        verify(mHomeLocalRepository, never()).getLocalBreweries()
        verify(mHomeLocalRepository).insertAll(ArgumentMatchers.anyList())
    }

    @Test
    fun getBreweriesData_whenNoNetworkConnection_shouldShowFromDatabase() {
        val list = mutableListOf<BreweryEntity>()
        `when`(mHomeLocalRepository.getLocalBreweries()).thenReturn(list)

        mUseCase.getBreweriesData(false).test().assertComplete()

        verify(mHomeRepository, never()).getBreweries()
        verify(mHomeLocalRepository).getLocalBreweries()
        verify(mHomeLocalRepository, never()).insertAll(list)
    }

    @Test
    fun getBreweriesData_whenError_shouldReturnError() {
        val exception = mock(Exception::class.java)
        `when`(mHomeRepository.getBreweries()).thenReturn(Single.error(exception))

        mUseCase.getBreweriesData(true).test().assertError(exception)
    }
}