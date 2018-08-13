package {{packageName}}.view.map

import {{packageName}}.repository.UserRepository

interface MapViewModelInputs

interface MapViewModelOutputs

class MapViewModel(private val userRepository: UserRepository)
    : MapViewModelInputs, MapViewModelOutputs