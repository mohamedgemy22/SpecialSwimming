package com.qersh.swimmig_schole_api.dp

import androidx.room.TypeConverter
import com.qersh.swimmig_schole_api.model.coach_photo
import org.json.JSONObject

class Converter {
    @TypeConverter
    fun fromSource(source: coach_photo): String {
        return JSONObject().apply {
            put("id", source.url)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): coach_photo {
        val json = JSONObject(source)
        return coach_photo(json.get("id") as String)
    }
}