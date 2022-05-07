package com.study.flowpractice.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.study.flowpractice.databinding.FragmentSharedFlowBinding
import com.study.flowpractice.viewmodel.SharedFlowViewModel
import com.study.flowpractice.viewmodel.UserViewModel

class SharedFlowFragment : Fragment() {

    private val viewModel by viewModels<SharedFlowViewModel>()

    private val mBinding: FragmentSharedFlowBinding by lazy {
        FragmentSharedFlowBinding.inflate(layoutInflater)
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
            ivStart.setOnClickListener{
                viewModel.startRefresh()
            }
            ivPause.setOnClickListener {
                viewModel.stopRefresh()
            }
        }

    }
}