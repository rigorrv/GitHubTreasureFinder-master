package com.pany.withrooms.model.repos

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterReposJson {

    @TypeConverter
    fun fromItemList(countryLang: List<Item?>?): String? {
        val type = object : TypeToken<List<Item?>?>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toItemList(countryLangString: String?): List<Item>? {
        val type = object : TypeToken<List<Item?>?>() {}.type
        return Gson().fromJson<List<Item>>(countryLangString, type)
    }

    @TypeConverter
    fun fromOwner(countryLang: Owner?): String? {
        val type = object : TypeToken<Owner>() {}.type
        return Gson().toJson(countryLang, type)
    }

    @TypeConverter
    fun toOwner(countryLangString: String?): Owner? {
        val type = object : TypeToken<Owner>() {}.type
        return Gson().fromJson<Owner>(countryLangString, type)
    }

    @TypeConverter
    fun stringListToJson(value: List<String>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToStringList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

}