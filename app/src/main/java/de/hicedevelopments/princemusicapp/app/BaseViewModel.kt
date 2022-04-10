package de.hicedevelopments.princemusicapp.app

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.hicedevelopments.princemusicapp.data.remote.ErrorHandler
import de.hicedevelopments.princemusicapp.data.remote.NetworkErr
import de.hicedevelopments.princemusicapp.util.ConnectionHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseViewModel : ViewModel(), ErrorHandler, KoinComponent {

    override val connectionHelper: ConnectionHelper by inject()

    val isLoading = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<NetworkErr>()

    override fun onNetworkErr(err: NetworkErr) {
        errorState.postValue(err)
    }

    fun asyncWithLoadingState(loadingState: MutableLiveData<Boolean> = isLoading, bgFunction: suspend () -> Unit): Job {
        val before = { loadingState.postValue(true) }
        val after = { loadingState.postValue(false) }

        return async(before, bgFunction, after)
    }

    private fun async(before: () -> Unit = {}, bgFunction: suspend () -> Unit, after: () -> Unit = {}) = viewModelScope.launch {
        catchError { before() }
        catchError { bgFunction() }
        catchError { after() }
    }

    private suspend fun catchError(toGuard: suspend () -> Any) {
        try {
            toGuard()
        } catch (e: Exception) {
            Log.e("ASYNC ERROR", e.message.toString())
            handleException(e)
        }
    }
}