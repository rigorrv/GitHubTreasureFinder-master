package com.pany.withrooms.model.users

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterJson {

    @TypeConverter
    fun fromItemsList(countryLang: List<Items?>?): String? {
        val type = object : TypeToken<List<Items?>?>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toItemsList(countryLangString: String?): List<Items>? {
        val type = object : TypeToken<List<Items?>?>() {}.type
        return Gson().fromJson<List<Items>>(countryLangString, type)
    }

    @TypeConverter
    fun fromDetails(countryLang: Details?): String? {
        val type = object : TypeToken<Details>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toDetails(countryLangString: String?): Details? {
        val type = object : TypeToken<Details>() {}.type
        return Gson().fromJson<Details>(countryLangString, type)
    }

    @TypeConverter
    fun stringListToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

}