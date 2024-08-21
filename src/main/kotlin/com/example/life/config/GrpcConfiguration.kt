package com.example.life.config

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import utility.UtilityServiceGrpcKt

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
    ): UtilityServiceGrpcKt.UtilityServiceCoroutineStub =
        UtilityServiceGrpcKt.UtilityServiceCoroutineStub(channel)
}
