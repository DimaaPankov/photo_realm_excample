package com.cameraxtutorial.app

import android.app.Application
import com.cameraxtutorial.madel.database.DataBase


class App : Application() {


    override fun onCreate() {
        super.onCreate()
        DataBase.init()

    }
}