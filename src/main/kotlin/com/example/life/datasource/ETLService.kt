package com.example.life.datasource

abstract class ETLService<T : Any> {
    abstract suspend fun extract(): T
    abstract suspend fun load(value: T)
    suspend fun fetchAndSave() {
        val newValue: T = extract()
        load(newValue)
    }
}
