package io.github.jenusdy.mars

import android.app.Application
import io.github.jenusdy.mars.data.AppContainer
import io.github.jenusdy.mars.data.DefaultAppContainer

class MarsPhotoApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}