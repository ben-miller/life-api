package com.example.life.service

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisService(
    private val redisTemplate: ReactiveRedisTemplate<String, Any>,
    private val objectMapper: ObjectMapper
) {
    suspend fun <T : Any> save(key: String, value: T) {
        redisTemplate.opsForValue().set(key, value).awaitSingle()
    }

    suspend fun <T : Any> get(key: String, clazz: Class<T>): T? {
        val jsonValue = redisTemplate.opsForValue().get(key).awaitSingle()
        return if (jsonValue != null) {
            objectMapper.convertValue(jsonValue, clazz)
        } else {
            null
        }
    }

    suspend fun delete(key: String) {
        redisTemplate.delete(key).awaitSingle()
    }

    suspend fun exists(key: String): Boolean {
        return redisTemplate.hasKey(key).awaitSingle()
    }

    suspend fun <T : Any> withCached(
        key: String,
        clazz: Class<T>,
        forceRefresh: Boolean = false,
        block: suspend () -> T
    ): T {
        if (exists(key) && !forceRefresh) {
            return get(key, clazz)!!
        } else {
            val res = block()
            save(key, res)
            return res
        }
    }
}
