package com.coffeeit.coffeemachine.ui.overview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.coffeeit.coffeemachine.databinding.CoffeeSettingsFragmentBinding
import com.coffeeit.coffeemachine.ui.base.BaseFragment
import com.coffeeit.coffeemachine.ui.extra.ExtraSelectionFragment
import com.coffeeit.coffeemachine.utils.onClick

/**
 * This fragment is for checking coffee order for the last time
 */
class OverviewFragment : BaseFragment() {
    private val viewModel: OverviewPageViewModel by viewModels()
    private lateinit var _adapter: OverViewListAdapter
    private var binding: CoffeeSettingsFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val order = dataModel.coffeeOrder
        if (order.machineId.isEmpty() || order.typeId.isEmpty() || order.sizeId.isEmpty()) {
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
        binding?.model = viewModel
        _adapter = OverViewListAdapter(
            viewModel.getInfoListData(
                dataModel.machineInfo,
                dataModel.coffeeOrder
            )
        )
        binding?.selectionRecyclerView?.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context)
        }
        binding?.brewButton?.onClick {
            it ?: return@onClick
            viewModel.onCLickBrewButton(dataModel.coffeeOrder, it)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val TAG = "OverviewFragment"
    }
}