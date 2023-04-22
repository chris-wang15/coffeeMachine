package com.coffeeit.coffeemachine.ui.extra

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
import com.coffeeit.coffeemachine.utils.onClick

/**
 * This fragment is for choosing different extra sub selections of coffee
 */
class ExtraSelectionFragment : BaseFragment() {
    private val viewModel: ExtraPageViewModel by viewModels()
    private lateinit var _adapter: ExtraListAdapter
    private var binding: CoffeeSettingsFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val order = dataModel.coffeeOrder
        if (order.sizeId.isEmpty() || order.sizeName.isEmpty()) {
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

        binding?.typePageTitle?.setText(R.string.extra_selection_title)
        binding?.brewButton?.setText(R.string.extra_brew_button)
        binding?.model = viewModel
        _adapter = ExtraListAdapter(
            viewModel.getExtraListData(
                dataModel.machineInfo,
                dataModel.coffeeOrder
            )
        )
        binding?.selectionRecyclerView?.apply {
            this.adapter = _adapter
            layoutManager = LinearLayoutManager(context)
        }
        binding?.brewButton?.onClick {
            viewModel.onCLickBrewButton(it)
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