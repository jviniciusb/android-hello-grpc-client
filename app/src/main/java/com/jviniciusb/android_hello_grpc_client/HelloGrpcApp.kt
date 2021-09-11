package com.jviniciusb.android_hello_grpc_client

import android.app.Application
import com.jviniciusb.android_hello_grpc_client.di.HelloGrpcDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.fileProperties

class HelloGrpcApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@HelloGrpcApp)
            modules(HelloGrpcDi.module)
            fileProperties()
        }
    }
}