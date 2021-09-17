package com.jviniciusb.android_hello_grpc_client.di

import com.jviniciusb.android_hello_grpc_client.domain.services.GreeterServiceClient
import com.jviniciusb.android_hello_grpc_client.domain.services.GreeterServiceClientImpl
import com.jviniciusb.android_hello_grpc_client.network.grpc.GrpcAndroidChannelProvider
import com.jviniciusb.android_hello_grpc_client.network.grpc.GrpcChannelProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object HelloGrpcDi {
    const val PROPERTY_GRPC_SERVER_ADDRESS_KEY = "grpcServerAddress"
    const val PROPERTY_GRPC_SERVER_PORT_KEY = "grpcServerPort"

    val module = module {
        single<GrpcChannelProvider> {
            GrpcAndroidChannelProvider(
                androidContext(),
                getProperty(PROPERTY_GRPC_SERVER_ADDRESS_KEY),
                getProperty(PROPERTY_GRPC_SERVER_PORT_KEY).toInt()
            )
        }
        factory<GreeterServiceClient> { GreeterServiceClientImpl(get()) }
    }
}
