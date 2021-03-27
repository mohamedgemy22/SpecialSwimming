package com.qersh.swimmig_schole_api.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.qersh.swimmig_schole_api.dp.Converter


@Entity(tableName = "notes_table")
data class note(
    val coach_name: String,
    val coach_location: String,
    val coach_price: Int,
    @PrimaryKey
    val coach_phone: Int,
    val coach_experience: String,
    @TypeConverters(Converter::class)
    val coach_photo: coach_photo
)

data class coach_photo(val url: String)