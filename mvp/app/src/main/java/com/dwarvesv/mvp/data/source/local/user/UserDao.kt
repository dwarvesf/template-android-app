package {{packageName}}.data.source.local.user

import android.arch.persistence.room.*
import {{packageName}}.data.model.User

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

    @Query("DELETE FROM Users")
    fun deleteAllUsers()

    @Transaction
    open fun updateData(users: List<User>) {
        deleteAllUsers()
        insertAll(users)
    }

}
