package com.study.flowpractice.download

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

object DownloadManager {
    fun download(url:String,file: File): Flow<DownloadStatus> {

        return flow{

        }
    }

}