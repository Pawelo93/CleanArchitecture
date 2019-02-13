package com.cleanarchitecture.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.cleanarchitecture.entity.User

class MainViewModel : ViewModel() {

    val user = MutableLiveData<User>()
}