package com.jviniciusb.android_hello_grpc_client.network.grpc

import android.content.Context
import io.grpc.ManagedChannel
import io.grpc.android.AndroidChannelBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor

interface GrpcChannellProvider {
    fun channel(): ManagedChannel
}

class GrpcAndroidChannellProvider(
    private val context: Context,
    private val address: String,
    private val port: Int
) : GrpcChannellProvider {

    private lateinit var _channel: ManagedChannel

    override fun channel(): ManagedChannel {
        if (!::_channel.isInitialized || _channel.isShutdown) {
            _channel = AndroidChannelBuilder
                .forAddress(address, port)
                .context(context)
                .usePlaintext()
                .executor(Dispatchers.IO.asExecutor())
                .build()
        }
        return _channel
    }
}