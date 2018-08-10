package {{packageName}}.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import {{packageName}}.data.model.User
import {{packageName}}.data.source.local.user.UserDao


// https://developer.android.com/training/data-storage/room/index.html
// https://medium.com/@ajaysaini.official/building-database-with-room-persistence-library-ecf7d0b8f3e9
@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var instance: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase? {
            if (instance == null) {
                synchronized(LocalDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "appDatabase")
                                .build()
                    }
                }
            }
            return instance
        }

    }
}