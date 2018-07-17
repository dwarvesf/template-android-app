package com.dwarvesv.mvp.data.source.local.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dwarvesv.mvvm.data.model.User

/**
 * Data Access Object for the users table.
 */
@Dao
interface UserDao {

    /**
     * Get a user by id.

     * @return the user from the table with a specific id.
     */
    @Query("SELECT * FROM Users WHERE id = :userId")
    fun getUserById(userId: String): User

    @Query("SELECT * FROM Users")
    fun getAllUsers(): List<User>

    /**
     * Insert a user in the database. If the user already exists, replace it.

     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userEntities: List<User>): Array<Long>


}
