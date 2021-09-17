package com.jviniciusb.android_hello_grpc_client.domain.services

import com.jviniciusb.android_hello_grpc_client.network.grpc.GrpcChannelProvider
import proto.greeter.v1.Greeter
import proto.greeter.v1.GreeterServiceGrpcKt

interface GreeterServiceClient {
    suspend fun sayHello(name: String): String
}

class GreeterServiceClientImpl(private val channelProvider: GrpcChannelProvider) :
    GreeterServiceClient {

    override suspend fun sayHello(name: String): String {
        return try {
            val stub = GreeterServiceGrpcKt.GreeterServiceCoroutineStub(channelProvider.channel())
            val request = Greeter.HelloRequest
                .newBuilder()
                .setName(name)
                .build()
            stub.sayHello(request).message
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}
