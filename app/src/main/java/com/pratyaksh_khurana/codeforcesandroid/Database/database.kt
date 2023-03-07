package com.pratyaksh_khurana.codeforcesandroid.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pratyaksh_khurana.codeforcesandroid.Dao.ProblemDao
import com.pratyaksh_khurana.codeforcesandroid.Entities.EachProblem

@Database(entities = [EachProblem::class], version = 1, exportSchema = false)
public abstract class database : RoomDatabase() {

    abstract fun problemDao(): ProblemDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: database? = null

        fun getDatabase(context: Context): database {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    database::class.java,
                    "database"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
