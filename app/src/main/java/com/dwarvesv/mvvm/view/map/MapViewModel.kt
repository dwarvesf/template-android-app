package com.dwarvesv.mvvm.view.map

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.dwarvesv.mvvm.repository.UserRepository

interface MapViewModelInputs

interface MapViewModelOutputs

class MapViewModel(var context: Context?, lifeCycle: LifecycleOwner, private val userRepository: UserRepository)
    : MapViewModelInputs, MapViewModelOutputs