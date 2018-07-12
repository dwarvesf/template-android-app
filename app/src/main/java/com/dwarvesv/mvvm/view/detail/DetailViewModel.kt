package com.dwarvesv.mvvm.view.detail

import com.dwarvesv.mvvm.repository.UserRepository

interface DetailViewModelInputs

interface DetailViewModelOutputs

class DetailViewModel(private val userRepository: UserRepository)
    : DetailViewModelInputs, DetailViewModelOutputs