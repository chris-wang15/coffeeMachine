package com.coffeeit.coffeemachine.ui.ordered

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.coffeeit.coffeemachine.MainApp
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.databinding.OrderSuccessFragmentBinding
import com.coffeeit.coffeemachine.modle.CoffeeOrder
import com.coffeeit.coffeemachine.modle.state.DataState
import com.coffeeit.coffeemachine.repository.MainRepository
import com.coffeeit.coffeemachine.ui.base.BaseViewModel
import com.coffeeit.coffeemachine.ui.base.collectLatestLifecycleFlow
import com.coffeeit.coffeemachine.utils.onClick
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Showing coffee is granted, and user can check all the ordered history
 */
class OrderSuccessFragment : Fragment() {
    private var binding: OrderSuccessFragmentBinding? = null
    private val state = MutableStateFlow<DataState<List<CoffeeOrder>>>(DataState.Rest)
    private lateinit var coffeeOrder: CoffeeOrder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(BaseViewModel.COFFEE_ORDER)) {
                val order = it.getParcelable<CoffeeOrder>(BaseViewModel.COFFEE_ORDER)
                if (order == null || order.sizeId.isEmpty() || order.sizeName.isEmpty()) {
                    Log.e(TAG, "illegal size: $order")
                }
                coffeeOrder = order ?: CoffeeOrder("")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OrderSuccessFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    private fun tryGetOrderHistory() {
        lifecycleScope.launch(Dispatchers.IO) {
            MainRepository.getOrderedCoffee().collectLatest { _state ->
                state.value = _state
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.buttonConfirm?.onClick {
            view.findNavController().navigate(R.id.global_navigate_to_first_page)
        }
        binding?.textviewCurrentOrder?.text = coffeeOrder.toString()
        observeState()
        tryGetOrderHistory()
    }

    private fun observeState() {
        collectLatestLifecycleFlow(state) {
            when (it) {
                is DataState.Success -> {
                    binding?.textviewFirst?.text = it.data.toString()
                }
                else -> binding?.textviewFirst?.text =
                    MainApp.App.getText(R.string.show_ordered_history)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val TAG = "OrderSuccessFragment"
    }
}