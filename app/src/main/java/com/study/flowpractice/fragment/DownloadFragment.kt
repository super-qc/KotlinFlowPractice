package com.study.flowpractice.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.study.flowpractice.databinding.FragmentDownloadBinding
import com.study.flowpractice.download.DownloadManager
import com.study.flowpractice.download.DownloadStatus
import kotlinx.coroutines.flow.collect
import java.io.File

/**
 * 从网络上下载图片，保存到 Android/data/包名/files/ 目录下
 */
class DownloadFragment : Fragment() {

    val URL="https://qniu.puncheers.com/1565148249957"

    private val mBinding: FragmentDownloadBinding by lazy {
        FragmentDownloadBinding.inflate(layoutInflater)
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

        lifecycleScope.launchWhenCreated {
            context?.apply {
                val file = File(getExternalFilesDir(null)?.path,"logo.jpg")

                DownloadManager.download(URL,file).collect {status->
                    when(status){
                        is DownloadStatus.Progress->{
                            mBinding.apply {
                                progressBar.progress=status.value
                                tvProgress.text="${status.value}%"
                            }
                        }
                        is DownloadStatus.Error->{
                            Toast.makeText(context,"下载错误",Toast.LENGTH_SHORT).show()
                        }
                        is DownloadStatus.Done -> {
                            mBinding.apply {
                                progressBar.progress=100
                                tvProgress.text="100%"
                            }
                            Toast.makeText(context,"下载完成",Toast.LENGTH_SHORT).show()
                        }
                        else->{
                            Log.d(this.javaClass.name,"下载失败")
                        }
                    }
                }
            }
        }
    }


}