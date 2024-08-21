package com.example.life.datasource

abstract class DataSource<T : Any> {
    abstract suspend fun fetch(): T
    abstract suspend fun save(value: T)
    suspend fun fetchAndSave() {
        val newValue: T = fetch()
        save(newValue)
    }
}
