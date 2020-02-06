package com.example.modulotechtest.room.devices

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.modulotechtest.model.device.Light


@Dao
interface LightDao {


    @Insert
    fun insert(light: Light)


    @Update
    fun update(light: Light)

    @Delete
    fun delete(light: Light)


    @Query("SELECT * FROM light_table")
    fun getAllLights(): LiveData<List<Light>>


}