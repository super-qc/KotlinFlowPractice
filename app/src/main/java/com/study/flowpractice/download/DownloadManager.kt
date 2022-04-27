package com.study.flowpractice.download

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import com.study.flowpractice.utils.copyTo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import java.io.IOException

object DownloadManager {
    fun download(url:String,file: File): Flow<DownloadStatus> {

        return flow{
            var request= Request.Builder().url(url).get().build()
            val response= OkHttpClient.Builder().build().newCall(request).execute()
            if(response.isSuccessful){
                response.body()!!.let {body->
                    val total = body.contentLength()
                    // 文件读写
                    file.outputStream().use { output->
                        val input = body.byteStream()
                        var emittedProcess = 0L
                        input.copyTo(output){ bytesCopied->
                            val progress = bytesCopied*100/total
                            if(progress-emittedProcess>5){
                                kotlinx.coroutines.delay(100)
                                emit(DownloadStatus.Progress(progress.toInt()))
                                emittedProcess = progress
                            }
                        }
                    }

                }
                emit(DownloadStatus.Done(file))
            }else{
                throw IOException(response.toString())
            }
        }.catch {
            file.delete()
            emit(DownloadStatus.Error(it))
        }.flowOn(Dispatchers.IO)
    }

}