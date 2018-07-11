package com.dwarvesv.mvvm.view.detail

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.dwarvesv.mvvm.repository.UserRepository

interface DetailViewModelInputs

interface DetailViewModelOutputs

class DetailViewModel(var context: Context?, lifeCycle: LifecycleOwner, private val userRepository: UserRepository)
    : DetailViewModelInputs, DetailViewModelOutputs