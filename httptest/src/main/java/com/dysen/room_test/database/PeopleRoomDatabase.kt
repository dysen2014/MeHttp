package com.dysen.room_test.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dysen.room_test.dao.PeopleDao
import com.dysen.room_test.entity.People
import kotlinx.coroutines.CoroutineScope

/**
 * dysen.
 * dy.sen@qq.com    10/23/20  9:44 AM

 * Info：数据库
 */
@Database(entities = [People::class], version = 1)
abstract class PeopleRoomDatabase : RoomDatabase() {
    abstract fun peopleDao(): PeopleDao

    companion object {

        @Volatile
        private var INSTANCE: PeopleRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PeopleRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PeopleRoomDatabase::class.java,
                    "people_database"
                ).allowMainThreadQueries()
//                    .addCallback(PeopleDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}