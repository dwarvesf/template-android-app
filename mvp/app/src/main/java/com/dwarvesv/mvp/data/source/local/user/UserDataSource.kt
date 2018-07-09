package com.dwarvesv.mvp.data.source.local.user


import com.dwarvesv.mvp.data.model.User
import io.reactivex.Single

/**
 * Access point for managing userEntity data.
 */
interface UserDataSource {

    fun insertAll(userList: List<User>): Single<Array<Long>>

    fun insertUser(user: User): Single<Long>

    fun getUserById(userId: String): Single<User>

    fun getAllUsers(): Single<List<User>>

    fun updateData(userList: List<User>)

}