package com.study.flowpractice.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.study.flowpractice.R
import com.study.flowpractice.adapter.UserAdapter
import com.study.flowpractice.databinding.FragmentHomeBinding
import com.study.flowpractice.databinding.FragmentUserBinding
import com.study.flowpractice.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collect


class UserFragment : Fragment() {

    private val viewModel by viewModels<UserViewModel>()

    private val mBinding: FragmentUserBinding by lazy {
        FragmentUserBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.apply {
            btnAddUser.setOnClickListener {
                viewModel.insert(
                    etUserId.text.toString(),
                    etFirstName.text.toString(),
                    etLastName.text.toString()
                )
            }
        }

        context?.let {
            val adapter = UserAdapter(it)
            mBinding.rvUser.adapter = adapter

            mBinding.rvUser.layoutManager =
                LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)

            lifecycleScope.launchWhenCreated {
                viewModel.getAll().collect { value ->
                    adapter.setData(value)
                }
            }
        }
    }
}