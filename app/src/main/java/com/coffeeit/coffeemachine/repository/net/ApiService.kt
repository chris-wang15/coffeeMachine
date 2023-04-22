package com.coffeeit.coffeemachine.repository.net

import com.coffeeit.coffeemachine.modle.data.ResponseData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

// Note: retrofit will not work if we use suspend fun :Bean or suspend fun :Deferred
interface ApiService {

    @GET("coffee-machine/{id}")
    fun getCoffeeMachine(@Path("id") id: String): Deferred<ResponseData>
}