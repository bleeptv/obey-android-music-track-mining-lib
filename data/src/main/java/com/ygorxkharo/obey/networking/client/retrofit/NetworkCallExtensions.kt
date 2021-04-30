package com.ygorxkharo.obey.networking.client.retrofit

import retrofit2.Call
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import com.ygorxkharo.obey.common.Failure
import com.ygorxkharo.obey.common.Result
import com.ygorxkharo.obey.common.Success
import com.ygorxkharo.obey.networking.HttpCodes
import com.ygorxkharo.obey.networking.errors.AuthenticationError
import com.ygorxkharo.obey.networking.errors.NetworkError
import com.ygorxkharo.obey.networking.errors.ServerError

/**
 * Extension function to handle different types of states when performing HTTP calls.
 * It can handle success and error states
 *
 * @receiver Call<T> Used in Retrofit used to make network calls with a response of generic type [T]
 * @param processResponse Callback triggered when a response is returned successfully from a request.
 * This is normally were the mapping from an external model (i.e. Moshi JSON object) to a domain
 * model object occurs
 * @return a [Result] success or failure state with a payload of generic type [R]
 */
fun <T: Any, R : Any> Call<T>.getResult(processResponse: (T) -> R): Result<R> {
    val call = clone()
    return try {
        val response = call.execute()
        val result = response.body()?.run { Success(processResponse(this)) }
        val errorResult = response.errorBody()?.run{
            val throwableResult = resolveNetworkErrorType(HttpException(response))
            Failure(throwableResult)
        } ?: Failure(Throwable("Response has no body"))

        result ?: errorResult
    } catch (error: Throwable) { // If it's an error unrelated to the network
        Failure(resolveNetworkErrorType(error))
    }
}

/**
 * Used to provide more concrete error types when making HTTP network calls, specifically on
 * network, authentication, and server-side related failures
 * @param error The Java/Android specific [Throwable] to be translated into 1 of the following error
 * events: network, authentication, and server-side related failures
 * @return a [Throwable] remapped to one of the failure events stated above, or the same error
 * throwable if it's not connect to either event
 */
private fun resolveNetworkErrorType(error: Throwable): Throwable = when {
    error is IOException || error is ConnectException -> NetworkError
    error is HttpException && error.code() in HttpCodes.serverErrors -> ServerError
    error is HttpException && error.code() in HttpCodes.authErrors -> AuthenticationError
    else -> error
}
