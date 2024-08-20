package com.example.life.datasource

interface DataSource<T : Any> {
    suspend fun fetch(): T
}
