package com.example.modulotechtest.utils

import android.app.Application
import com.example.modulotechtest.R
import com.example.modulotechtest.model.User
import com.example.modulotechtest.model.device.Devices
import com.example.modulotechtest.model.device.Heater
import com.example.modulotechtest.model.device.Light
import com.example.modulotechtest.model.device.RollerShutter
import org.json.JSONObject
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class JsonUtils @Inject constructor(val application: Application) {


    // load json file and return a string
    private fun readFile(): String {
        val ins = application.resources.openRawResource(R.raw.data)

        return ins.readBytes().toString(Charset.defaultCharset())
    }

    // parsing Devices list from json file
    fun parseDevices(): ArrayList<Devices> {
        val deviceList = ArrayList<Devices>()
        val jsonFile = readFile()

        val mainJson = JSONObject(jsonFile)

        val devicesJsonArray = mainJson.getJSONArray("devices")
        var i = 0
        while (i < devicesJsonArray.length()) {
            val device = devicesJsonArray.getJSONObject(i)
            when (device.getString("productType")) {
                "Light" -> deviceList.add(
                    Light(
                        device.getInt("id"),
                        device.getString("deviceName"),
                        device.getString("mode"),
                        device.getInt("intensity")
                    )
                )

                "Heater" -> deviceList.add(
                    Heater(
                        device.getInt("id"),
                        device.getString("deviceName"),
                        device.getString("mode"),
                        device.getDouble("temperature")
                    )
                )

                "RollerShutter" -> deviceList.add(
                    RollerShutter(
                        device.getInt("id"),
                        device.getString("deviceName"),
                        device.getInt("position")
                    )
                )
            }
            i += 1
        }
        return deviceList

    }

    // parsing User information from json file
    fun parseUser(): User {
        val jsonFile = readFile()
        val mainJson = JSONObject(jsonFile)
        val userJson = mainJson.getJSONObject("user")
        val address = userJson.getJSONObject("address")


        return User(
            userJson.getString("firstName"),
            userJson.getString("lastName"),
            address.getString("city"),
            address.getInt("postalCode"),
            address.getString("street"),
            address.getString("streetCode"),
            address.getString("country"),
            userJson.getLong("birthDate")
        )
    }

}
