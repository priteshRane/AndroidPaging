package com.ransoft.androidpaging.sampleone.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ransoft.androidpaging.sampleone.data.model.PreviousRequest
import com.ransoft.androidpaging.sampleone.data.repositories.SampleOneRepository
import com.ransoft.androidpaging.util.Coroutines
import com.ransoft.androidpaging.util.NoInternetException
import java.lang.Exception
import javax.inject.Inject

class SampleOneViewModel @Inject constructor(
    val sampleOneRepository: SampleOneRepository
): ViewModel() {
    private val LOG_TAG_API = "API"

    fun callPreviousRequestApi() {
        Coroutines.main {
            try {
                val previousRequest = sampleOneRepository.previousRequest()
                Log.d(LOG_TAG_API, "Previous request: " + previousRequest.previousRequests.get(1))
            } catch (e: NoInternetException) {
                Log.d(LOG_TAG_API, "NoInternetException in SampleOneViewModel: " + e)
            } catch (e: Exception) {
                Log.d(LOG_TAG_API, "Exception in SampleOneViewModel: " + e.toString())
            }
        }
    }
}