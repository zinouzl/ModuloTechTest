package com.example.modulotechtest.model.device

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "light_table")
data class Light(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "deviceName")
    val deviceName: String,
    @ColumnInfo(name = "mode")
    val mode: String,
    @ColumnInfo(name = "intensity")
    val intensity: Int
) : Devices {
    override fun getType() = Devices.TYPE_LIGHT
}