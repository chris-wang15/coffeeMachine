package com.coffeeit.coffeemachine.ui.main

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.coffeeit.coffeemachine.MainApp
import com.coffeeit.coffeemachine.R
import com.coffeeit.coffeemachine.databinding.MainFragmentBinding
import com.coffeeit.coffeemachine.modle.state.DataState
import com.coffeeit.coffeemachine.ui.base.DataViewModel
import com.coffeeit.coffeemachine.utils.onClick
import kotlinx.coroutines.flow.StateFlow

/**
 * This fragment is to show the first page, click the NFC button to simulate that we are
 * connecting a coffee machine
 */
class MainFragment : Fragment() {
    private val dataModel: DataViewModel by activityViewModels()
    lateinit var viewModel: MainPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val factory = ViewModelFactory(dataModel.connectState)
        viewModel = ViewModelProvider(this, factory)[MainPageViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.model = viewModel
        binding.nfcImg.onClick {
            if (dataModel.connectState.value is DataState.Success) {
                it ?: return@onClick
                it.findNavController().navigate(R.id.TypeSelectionFragment)
            } else {
                dataModel.connectMachine()
            }
        }
        binding.nfcText.paint.flags = Paint.UNDERLINE_TEXT_FLAG
        binding.nfcText.onClick {
            val builder = AlertDialog
                .Builder(this.requireContext())
                .setTitle(R.string.use_description_title)
                .setMessage(R.string.use_description)
                .setNegativeButton(
                    MainApp.App.getString(R.string.confirm),
                    null
                )
                .show()
            builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                Color.parseColor("#000000")
            )
        }
        return root
    }
}

@Suppress("UNCHECKED_CAST")
private class ViewModelFactory(
    private val state: StateFlow<DataState<String>>
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainPageViewModel(state) as T
    }
}