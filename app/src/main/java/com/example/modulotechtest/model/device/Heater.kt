package com.example.modulotechtest.model.device

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "heater_table")
data class Heater
    (
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "deviceName")
    val deviceName: String,
    @ColumnInfo(name = "mode")
    val mode: String,
    @ColumnInfo(name = "temperature")
    val temperature: Double
) : Devices {
    override fun getType() = Devices.TYPE_Heater
}