package com.example.life.etl

abstract class ETLService<T : Any>(
    protected val sourceName: String
) {
    abstract suspend fun extract(): T
    abstract suspend fun load(lastSample: T)
    suspend fun fetchAndSave(): T {
        val newValue: T = extract()
        load(newValue)
        return newValue
    }
}
