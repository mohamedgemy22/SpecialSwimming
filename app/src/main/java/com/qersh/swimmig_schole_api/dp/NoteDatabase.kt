package com.qersh.swimmig_schole_api.dp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.qersh.swimmig_schole_api.model.note

@Database(
    entities = arrayOf(note::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
public abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDoa(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}