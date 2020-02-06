package com.example.modulotechtest.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.modulotechtest.BaseApp
import com.example.modulotechtest.di.DaggerAppComponent
import com.example.modulotechtest.model.User
import com.example.modulotechtest.model.device.Heater
import com.example.modulotechtest.model.device.Light
import com.example.modulotechtest.model.device.RollerShutter
import com.example.modulotechtest.room.devices.HeaterDao
import com.example.modulotechtest.room.devices.LightDao
import com.example.modulotechtest.room.devices.RollerShutterDao
import com.example.modulotechtest.room.user.UserDao
import javax.inject.Singleton

@Singleton
@Database(entities = [User::class, Heater::class, Light::class, RollerShutter::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    @Singleton
    class PopulateDbAsyncTask constructor(appDatabase: AppDatabase, baseApp: BaseApp) :
        AsyncTask<Unit, Unit, Unit>() {

        //private val TAG = "PopulateDbAsyncTask"

        private val jsonUtils =
            DaggerAppComponent.builder().application(baseApp).build().getJsonUtils()
        private val userDao = appDatabase.userDao()
        private val lightDao = appDatabase.lightDao()
        private val heaterDao = appDatabase.heaterDao()
        private val rollerShutterDao = appDatabase.rollerShutterDao()
        override fun doInBackground(vararg params: Unit?) {

            val deviceList = jsonUtils.parseDevices()
            deviceList.forEach {
                when (it) {
                    is Light -> lightDao.insert(it)
                    is Heater -> heaterDao.insert(it)
                    is RollerShutter -> rollerShutterDao.insert(it)
                }
            }
            val user = jsonUtils.parseUser()
           //android.util.Log.d(TAG, user.firstName)

            userDao.insert(user)


        }


    }


    abstract fun userDao(): UserDao

    abstract fun heaterDao(): HeaterDao

    abstract fun lightDao(): LightDao

    abstract fun rollerShutterDao(): RollerShutterDao


    companion object {


        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                instance?.let { PopulateDbAsyncTask(it, application).execute() }


            }


        }


        @Volatile
        private var instance: AppDatabase? = null
        lateinit var application: BaseApp
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }.also {
                application = context as BaseApp
            }

        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "modulo_database.db"
        )
            .fallbackToDestructiveMigration()
            .addCallback(roomCallback)
            .build()


    }


}