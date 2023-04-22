package com.coffeeit.coffeemachine.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

/**
 * base fragment for showing coffee selections
 */
abstract class BaseFragment : Fragment() {
    val dataModel: DataViewModel by activityViewModels()
}