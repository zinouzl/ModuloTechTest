package com.example.modulotechtest.room.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.modulotechtest.model.User


@Dao
interface UserDao{


    @Insert
    fun insert(user: User)


    @Update
    fun update(user: User)

    @Query("SELECT * FROM users_table LIMIT 1")
    fun getUser():LiveData<User>
}