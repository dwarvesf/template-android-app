package com.dwarvesv.mvvm.data.local.user

import android.content.Context
import com.dwarvesv.mvp.data.source.local.user.UserDao
import com.dwarvesv.mvvm.data.local.LocalDatabase
import com.dwarvesv.mvvm.data.model.User
import io.reactivex.Single

class UserLocalDataSource(context: Context) : UserDataSource {

    private var mUserDao: UserDao

    init {
        mUserDao = LocalDatabase.getInstance(context)?.userDao()!!
    }

    companion object {

        private var instance: UserLocalDataSource? = null

        fun getInstance(context: Context): UserLocalDataSource? {

            if (instance == null) {
                synchronized(UserLocalDataSource::class.java) {
                    if (instance == null) {
                        instance = UserLocalDataSource(context)
                    }
                }
            }
            return instance
        }
    }

    override fun insertAll(userList: List<User>): Single<Array<Long>> {
        return Single.fromCallable { mUserDao.insertAll(userList) }
    }

    override fun insertUser(user: User): Single<Long> {
        return Single.fromCallable { mUserDao.insertUser(user) }
    }

    override fun getUserById(userId: String): Single<User> {
        return Single.fromCallable { mUserDao.getUserById(userId) }
    }

    override fun getAllUsers(): Single<List<User>> {
        return Single.fromCallable { mUserDao.getAllUsers() }
    }

}