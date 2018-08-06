package com.dwarvesv.mvvm.view.map

import com.dwarvesv.mvvm.repository.UserRepository

interface MapViewModelInputs

interface MapViewModelOutputs

class MapViewModel(private val userRepository: UserRepository)
    : MapViewModelInputs, MapViewModelOutputs