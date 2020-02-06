package com.example.modulotechtest.model.device

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "rollerShutter_table")
class RollerShutter(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id:Int,
    @ColumnInfo(name = "deviceName")
    val deviceName:String,
    @ColumnInfo(name = "position")
    val position:Int
) : Devices {
    override fun getType() = Devices.TYPE_ROLLER_SHUTTER
}