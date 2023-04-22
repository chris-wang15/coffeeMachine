package com.coffeeit.coffeemachine.ui.size

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.databinding.CoffeeSettingsFragmentBinding
import com.coffeeit.coffeemachine.ui.base.BaseFragment

/**
 * This fragment is for choosing different size of coffee
 */
class SizeSelectionFragment : BaseFragment() {
    private val viewModel: SizePageViewModel by viewModels()
    private lateinit var _adapter: SizeListAdapter
    private var binding: CoffeeSettingsFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val order = dataModel.coffeeOrder
        if (order.typeId.isEmpty()) {
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
        binding?.typePageTitle?.setText(R.string.size_selection_title)
        binding?.model = viewModel
        _adapter = SizeListAdapter(
            viewModel.getSizeListData(
                dataModel.machineInfo,
                dataModel.coffeeOrder
            )
        )
        binding?.selectionRecyclerView?.apply {
            adapter = _adapter
            layoutManager = LinearLayoutManager(context)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        private const val TAG = "SizeSelectionFragment"
    }
}