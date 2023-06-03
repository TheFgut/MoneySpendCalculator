package com.example.moneyspendcalculator.data_manage

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

class DataLoaderAndSaver<T>(jsonFileName: String, Class: Class<T>, context: Context) {
    var jsonFileName: String
    private val context: Context
    private val pathFile: File
    private val Class: Class<T>

    init {
        var jsonFileName = jsonFileName
        this.Class = Class
        this.context = context
        pathFile = context.filesDir
        val parsed = jsonFileName.split(".".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        jsonFileName += if (parsed.size > 1 && parsed[parsed.size - 1] !== "txt") {
            ".txt"
        } else {
            ".txt"
        }
        this.jsonFileName = jsonFileName
    }

    fun LoadData(): ArrayList<T>? {
        val gson = GsonBuilder().setLenient()
            .create()
        val dat = ArrayList<T>()
        try {
            val ff = File(pathFile, jsonFileName)
            val fr = FileReader(ff)
            val br = BufferedReader(fr)
            val data = ""
            var line: String? = null
            while (br.readLine().also { line = it } != null) {
                if (line != null) {
                    dat.add(gson.fromJson(line, Class) as T)
                }
            }
            br.close()
        } catch (ie: IOException) {
            println("Data is not loaded")
            ie.printStackTrace()
            return null
        }
        return dat
    }

    fun SaveData(data: Array<T>) {
        val gson = Gson()
        var json: String? = ""
        for (i in data.indices) {
            json += gson.toJson(data[i])
            if (i < data.size - 1) {
                json += "\n"
            }
        }
        val ff = File(pathFile, jsonFileName)
        try {
            FileWriter(ff).use { fileWriter ->
                fileWriter.write(json)
                println("String saved to file successfully.")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}