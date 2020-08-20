package com.ransoft.androidpaging.ui.networkonly

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ransoft.androidpaging.data.model.PreviousRequest
import com.ransoft.androidpaging.data.repositories.NetworkOnlyRepository
import com.ransoft.androidpaging.util.Coroutines
import com.ransoft.androidpaging.util.NoInternetException
import javax.inject.Inject

class PreviousRequestDataSource @Inject constructor(
    val networkOnlyRepository: NetworkOnlyRepository
) : PageKeyedDataSource<Int, PreviousRequest>() {

    var LOG_TAG_API = "API"
    var PAGE_SIZE = 10
    var FIRST_PAGE = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PreviousRequest>
    ) {
        Coroutines.main {
            try {
                val previousRequestResponse =
                    networkOnlyRepository.previousRequestResponse(FIRST_PAGE, PAGE_SIZE)
                callback.onResult(previousRequestResponse.previousRequests, null, FIRST_PAGE + 1)
            } catch (e: NoInternetException) {
                Log.d(LOG_TAG_API, "NoInternetException in SampleOneViewModel: " + e)
            } catch (e: Exception) {
                Log.d(LOG_TAG_API, "Exception in SampleOneViewModel: " + e.toString())
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PreviousRequest>) {
        Coroutines.main {
            try {
                val previousRequestResponse =
                    networkOnlyRepository.previousRequestResponse(params.key, PAGE_SIZE)
                if (params.key <= previousRequestResponse.totalPages!!) {
                    callback.onResult(previousRequestResponse.previousRequests, params.key + 1)
                }
            } catch (e: NoInternetException) {
                Log.d(LOG_TAG_API, "NoInternetException in SampleOneViewModel: " + e)
            } catch (e: Exception) {
                Log.d(LOG_TAG_API, "Exception in SampleOneViewModel: " + e.toString())
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PreviousRequest>) {
        Coroutines.main {
            try {
                val previousRequestResponse =
                    networkOnlyRepository.previousRequestResponse(params.key, PAGE_SIZE)
                if (params.key > 1) {
                    callback.onResult(previousRequestResponse.previousRequests, params.key - 1)
                }
            } catch (e: NoInternetException) {
                Log.d(LOG_TAG_API, "NoInternetException in SampleOneViewModel: " + e)
            } catch (e: Exception) {
                Log.d(LOG_TAG_API, "Exception in SampleOneViewModel: " + e.toString())
            }
        }
    }
}