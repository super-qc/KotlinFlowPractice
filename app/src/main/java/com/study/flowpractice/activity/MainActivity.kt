package com.study.flowpractice.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.study.flowpractice.R
import com.study.flowpractice.databinding.ActivityMainBinding
import com.study.flowpractice.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {
    private val mBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

}