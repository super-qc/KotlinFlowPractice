package com.study.flowpractice.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.study.flowpractice.R
import com.study.flowpractice.databinding.FragmentStateFlowBinding
import com.study.flowpractice.databinding.FragmentUserBinding
import com.study.flowpractice.viewmodel.StateFlowViewModel
import com.study.flowpractice.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collect

class StateFlowFragment : Fragment() {

    private val viewModel by viewModels<StateFlowViewModel>()

    private val mBinding: FragmentStateFlowBinding by lazy {
        FragmentStateFlowBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.apply {
            btnPlus.setOnClickListener {
                viewModel.plusNumber()
            }
            btnMinus.setOnClickListener {
                viewModel.minusNumber()
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.number.collect { value ->
                mBinding.tvNumber.text="$value"
            }
        }
    }
}