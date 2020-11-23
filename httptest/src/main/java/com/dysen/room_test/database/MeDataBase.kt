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
 * dy.sen@qq.com    10/23/20  9:47 AM

 * Info：
 */
@Database(entities = [People::class], version = 1)
abstract class MeDataBase: RoomDatabase() {
    abstract fun peopleDao(): PeopleDao
    companion object {

        @Volatile
        private var INSTANCE: MeDataBase? = null

//        fun getDatabase(context: Context, scope: CoroutineScope ): MeDataBase {
        fun getDatabase(context: Context): MeDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MeDataBase::class.java,
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