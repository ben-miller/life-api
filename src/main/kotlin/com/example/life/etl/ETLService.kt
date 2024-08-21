package com.example.life.etl

abstract class ETLService<T : Any> {
    abstract suspend fun extract(): T
    abstract suspend fun load(value: T)
    suspend fun fetchAndSave() {
        val newValue: T = extract()
        load(newValue)
    }
}
