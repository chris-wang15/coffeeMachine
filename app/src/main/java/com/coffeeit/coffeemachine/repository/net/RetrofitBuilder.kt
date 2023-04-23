package com.coffeeit.coffeemachine.repository.net

import com.coffeeit.coffeemachine.modle.CoffeeOrder
import com.coffeeit.coffeemachine.modle.data.ResponseData
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    val api: ApiService = getRetrofit().create(ApiService::class.java)

    private const val BASE_URL = "https://darkroastedbeans.coffeeit.nl/"

    private fun getRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(
                5000, TimeUnit.MILLISECONDS
            )
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build()
    }

    @Deprecated("For test only, to be deleted")
    suspend fun getFakeData(): ResponseData {
        delay(1000)
        return Gson().fromJson(FAKE_INFO, ResponseData::class.java)
    }

    // Note: this is a fake method to simulate try connecting coffee machine and get machine id
    suspend fun tryConnectMachine(): String {
        delay(1000)
        return FAKE_MACHINE_ID
    }

    // Note: this is a fake method to simulate the order has been send to coffee machine
    suspend fun sendCoffeeOrderToMachine(order: CoffeeOrder) {
        delay(1000)
    }

    private const val FAKE_MACHINE_ID = "60ba1ab72e35f2d9c786c610"

    private const val FAKE_INFO = """
{
  "code": "200",
  "_id": "60ba1ab72e35f2d9c786c610",
  "types": [
    {
      "_id": "60ba1a062e35f2d9c786c56d",
      "name": "Ristretto",
      "sizes": [
        "60ba18d13ca8c43196b5f606",
        "60ba3368c45ecee5d77a016b"
      ],
      "extras": [
        "60ba197c2e35f2d9c786c525"
      ]
    },
    {
      "_id": "60be1db3c45ecee5d77ad890",
      "name": "Espresso",
      "sizes": [
        "60ba3368c45ecee5d77a016b",
        "60ba33dbc45ecee5d77a01f8"
      ],
      "extras": [
        "60ba34a0c45ecee5d77a0263"
      ]
    },
    {
      "_id": "60be1eabc45ecee5d77ad960",
      "name": "Cappuccino",
      "sizes": [
        "60ba18d13ca8c43196b5f606",
        "60ba3368c45ecee5d77a016b",
        "60ba33dbc45ecee5d77a01f8"
      ],
      "extras": [
        "60ba197c2e35f2d9c786c525",
        "60ba34a0c45ecee5d77a0263"
      ]
    }
  ],
  "sizes": [
    {
      "_id": "60ba18d13ca8c43196b5f606",
      "name": "Large",
      "__v": 0
    },
    {
      "_id": "60ba3368c45ecee5d77a016b",
      "name": "Venti"
    },
    {
      "_id": "60ba33dbc45ecee5d77a01f8",
      "name": "Tall"
    }
  ],
  "extras": [
    {
      "_id": "60ba197c2e35f2d9c786c525",
      "name": "Select the amount of sugar",
      "subselections": [
        {
          "_id": "60ba194dfdd5e192e14eaa75",
          "name": "A lot"
        },
        {
          "_id": "60ba195407e1dc8a4e33b5e5",
          "name": "Normal"
        }
      ]
    },
    {
      "_id": "60ba34a0c45ecee5d77a0263",
      "name": "Select type of milk",
      "subselections": [
        {
          "_id": "611a1adeff35e4db9df19667",
          "name": "Soy"
        },
        {
          "_id": "60ba348d8c75424ac5ed259e",
          "name": "Oat"
        },
        {
          "_id": "60ba349a869d7a04642b41f4",
          "name": "Cow"
        }
      ]
    }
  ]
}
    """
}