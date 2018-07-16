package com.dwarvesv.mvvm.view.home

import com.dwarvesv.mvvm.repository.UserRepository

interface HomeViewModelInputs

interface HomeViewModelOutputs

class HomeViewModel(private val userRepository: UserRepository)
    : HomeViewModelInputs, HomeViewModelOutputs