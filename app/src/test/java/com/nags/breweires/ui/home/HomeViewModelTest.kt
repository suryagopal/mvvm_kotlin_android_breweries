package com.nags.breweires.ui.home

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import com.nags.breweires.business.home.HomeUseCase
import com.nags.breweires.business.home.model.BreweryDomainModel
import com.nags.breweires.db.BreweriesDatabase
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.concurrent.Callable


@RunWith(JUnit4::class)
class HomeViewModelTest {

    private lateinit var mViewModel: HomeViewModel
    private lateinit var mDatabase: BreweriesDatabase

    @Mock lateinit var mBreweriesObserver: Observer<List<BreweryDomainModel>>
    @Mock lateinit var mLoaderObserver: Observer<Boolean>
    @Mock lateinit var mErrorObserver: Observer<Exception>
    var mException = mock(Exception::class.java)
    var mContext: Context = mock(Context::class.java)

    var mHomeUseCase: HomeUseCase = mock(HomeUseCase::class.java)

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            mContext, BreweriesDatabase::class.java
        ).build()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        mViewModel = HomeViewModel(mHomeUseCase)
        mViewModel.getBreweries().observeForever(mBreweriesObserver)
        mViewModel.getLoader().observeForever(mLoaderObserver)
        mViewModel.onError().observeForever(mErrorObserver)
    }

    @Test
    fun getData_whenNetworkAvailable_shouldReturnSuccess() {
        mockRxJava()
        val list = mutableListOf<BreweryDomainModel>()
        `when`(mHomeUseCase.getBreweriesData(true)).thenReturn(Single.just(list))

        mViewModel.initCall(true)

        assertTrue(mViewModel.getBreweries().hasObservers())
        assertNotNull(mViewModel.getBreweries())
        verify(mLoaderObserver, times(3)).onChanged(ArgumentMatchers.anyBoolean())
    }

    @Test
    fun getData_whenNetworkNotAvailable_shouldReturnSuccess() {
        mockRxJava()
        val list = mutableListOf<BreweryDomainModel>()
        `when`(mHomeUseCase.getBreweriesData(false)).thenReturn(Single.just(list))

        mViewModel.initCall(false)

        assertTrue(mViewModel.getBreweries().hasObservers())
        assertNotNull(mViewModel.getBreweries())
        verify(mLoaderObserver, times(3)).onChanged(ArgumentMatchers.anyBoolean())
    }

    @Test
    fun getData_whenNetworkAvailable_shouldReturnError() {
        mockRxJava()
        `when`(mHomeUseCase.getBreweriesData(true)).thenReturn(Single.error(mException))
        mViewModel.initCall(true)

        assertTrue(mViewModel.getBreweries().hasObservers())
        verify(mLoaderObserver, times(3)).onChanged(ArgumentMatchers.anyBoolean())
        verify(mErrorObserver).onChanged(mException)
    }

    private fun mockRxJava() {
        RxJavaPlugins.setIoSchedulerHandler { scheduler: Scheduler? -> Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { schedulerCallable: Callable<Scheduler?>? -> Schedulers.trampoline() }
    }

    @Test
    fun getBreweries() {
    }

    @Test
    fun initCall() {
    }
}