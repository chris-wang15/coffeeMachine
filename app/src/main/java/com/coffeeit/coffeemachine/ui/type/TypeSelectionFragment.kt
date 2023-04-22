package com.coffeeit.coffeemachine.ui.type

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.databinding.CoffeeSettingsFragmentBinding
import com.coffeeit.coffeemachine.modle.CoffeeMachine
import com.coffeeit.coffeemachine.modle.state.DataState
import com.coffeeit.coffeemachine.ui.base.BaseFragment
import com.coffeeit.coffeemachine.utils.onClick
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * This fragment is for choosing different type of coffee
 */
class TypeSelectionFragment : BaseFragment() {
    private lateinit var viewModel: TypePageViewModel
    private lateinit var _adapter: TypeListAdapter
    private var binding: CoffeeSettingsFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val order = dataModel.coffeeOrder
        if (order.machineId.isEmpty()) {
            Log.e(TAG, "illegal order: $order")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CoffeeSettingsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.noResultView?.onClick {
            dataModel.fetchMachineInfo()
        }
        _adapter = TypeListAdapter()
        binding?.selectionRecyclerView?.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context)
        }
        binding?.typePageTitle?.setText(R.string.type_selection_title)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = ViewModelFactory(dataModel.machineState)
        viewModel = ViewModelProvider(this, factory)[TypePageViewModel::class.java]
        binding?.model = viewModel
        observeState()
        dataModel.fetchMachineInfo()
    }

    private fun observeState() {
        lifecycleScope.launch {
            dataModel.machineState.collect {
                when (it) {
                    is DataState.Success -> {
                        renderList(it.data)
                    }
                    else -> {}
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderList(info: CoffeeMachine) {
        _adapter.addData(info.types)
        _adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val TAG = "TypeSelectionFragment"
    }
}

@Suppress("UNCHECKED_CAST")
private class ViewModelFactory(
    private val state: StateFlow<DataState<CoffeeMachine>>
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TypePageViewModel(state) as T
    }
}