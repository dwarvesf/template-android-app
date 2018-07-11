package com.dwarvesv.mvvm.view.home

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.dwarvesv.mvvm.repository.UserRepository

interface HomeViewModelInputs

interface HomeViewModelOutputs

class HomeViewModel(var context: Context?, lifeCycle: LifecycleOwner, private val userRepository: UserRepository)
    : HomeViewModelInputs, HomeViewModelOutputs