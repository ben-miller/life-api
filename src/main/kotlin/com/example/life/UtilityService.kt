package com.example.life

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import utility.UtilityServiceGrpc
import utility.UtilityServiceOuterClass.EmptyRequest
import utility.UtilityServiceOuterClass.ObsidianMetrics

interface UtilityService {
    fun getObsidianMetrics(): ObsidianMetrics
}

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

private val EmptyReq = EmptyRequest.newBuilder().build()

@Service
class UtilityServiceImpl(
    private val channel: ManagedChannel,
    private val stub: UtilityServiceGrpc.UtilityServiceBlockingStub
) : UtilityService {
    override fun getObsidianMetrics() = stub.getObsidianMetrics(EmptyReq)
}
