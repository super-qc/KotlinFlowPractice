package com.study.flowpractice.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.study.flowpractice.db.AppDatabase
import com.study.flowpractice.db.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {


    fun insert(uid: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            AppDatabase.geInstance(getApplication())
                .userDao().insert(User(uid.toInt(), firstName, lastName))
            Log.d(this.javaClass.name, "insert user:$uid,$firstName,$lastName")
        }
    }

    fun getAll(): Flow<List<User>> {
        return AppDatabase.geInstance(getApplication())
            .userDao().getAll()
            .catch { e -> e.printStackTrace() }
            .flowOn(Dispatchers.IO)
    }

}