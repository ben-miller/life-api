package com.example.life.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(KotlinModule.Builder().build())
    }

    @Bean
    fun reactiveRedisTemplate(
        connectionFactory: ReactiveRedisConnectionFactory
    ): ReactiveRedisTemplate<String, Any> {

        // Configure Jackson2JsonRedisSerializer with the custom ObjectMapper
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(Any::class.java)

        // Create serialization context using Jackson2JsonRedisSerializer
        val serializationContext = RedisSerializationContext
            .newSerializationContext<String, Any>(StringRedisSerializer())
            .value(jackson2JsonRedisSerializer)  // Use Jackson2JsonRedisSerializer here
            .build()

        return ReactiveRedisTemplate(connectionFactory, serializationContext)
    }
}
