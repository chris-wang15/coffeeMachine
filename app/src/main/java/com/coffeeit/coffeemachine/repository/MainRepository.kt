package com.coffeeit.coffeemachine.repository

import android.util.Log
import com.coffeeit.coffeemachine.modle.CoffeeMachine
import com.coffeeit.coffeemachine.modle.CoffeeOrder
import com.coffeeit.coffeemachine.modle.state.DataState
import com.coffeeit.coffeemachine.repository.net.RetrofitBuilder
import com.coffeeit.coffeemachine.repository.room.CacheMapper
import com.coffeeit.coffeemachine.repository.room.OrderListDao
import com.coffeeit.coffeemachine.repository.room.RoomModule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object MainRepository {

    private var dao: OrderListDao = RoomModule.dao

    private val mapper = CacheMapper()

    fun getCoffeeMachineInfo(machineId: String): Flow<DataState<CoffeeMachine>> = flow {
        try {
            emit(DataState.Loading)
            val netData = RetrofitBuilder.getFakeData()
//            val netJob = RetrofitBuilder.api.getCoffeeMachine(machineId)
//            val netData = netJob.await()
            val machineData = CoffeeMachine.create(netData)
            emit(DataState.Success(machineData))
        } catch (e: Exception) {
            Log.e(TAG, "getCoffeeMachine error", e)
            emit(DataState.Error(e))
        }
    }

    fun getOrderedCoffee(): Flow<DataState<List<CoffeeOrder>>> = flow {
        try {
            emit(DataState.Loading)
            val list = dao.getOrderList()
            val orderList = mapper.mapFromEntityList(list)
            emit(DataState.Success(orderList))
        } catch (e: Exception) {
            Log.e(TAG, "getCoffeeMachine error", e)
            emit(DataState.Error(e))
        }
    }

    suspend fun sendCoffeeOrder(order: CoffeeOrder) {
        dao.insert(mapper.mapToEntity(order))
        RetrofitBuilder.sendCoffeeOrderToMachine(order)
    }

    fun tryConnectMachine(): Flow<DataState<String>> = flow {
        try {
            emit(DataState.Loading)
            val machineId = RetrofitBuilder.tryConnectMachine()
            emit(DataState.Success(machineId))
        } catch (e: Exception) {
            Log.e(TAG, "tryConnectMachine error", e)
            emit(DataState.Error(e))
        }
    }

    private const val TAG = "MainRepository"
}