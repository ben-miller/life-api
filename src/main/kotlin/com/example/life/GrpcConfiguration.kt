package com.example.life

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import utility.UtilityServiceGrpc

@Configuration
class GrpcConfiguration {
    @Bean
    fun managedChannel(): ManagedChannel = ManagedChannelBuilder
        .forAddress("localhost", 50052)
        .usePlaintext()
        .build()

    @Bean
    fun utilityServiceStub(
        channel: ManagedChannel
    ): UtilityServiceGrpc.UtilityServiceBlockingStub = UtilityServiceGrpc.newBlockingStub(channel)
}
