package com.example.modulotechtest.utils



interface DeviceUpdatedListener {

    fun heaterUpdated(id:Int,name:String,mode:String,temperature:Double)

    fun lightUpdated(id: Int,name: String,mode: String,intensity:Int)

    fun rollerShutterUpdated(id: Int,name: String,position: Int)
}