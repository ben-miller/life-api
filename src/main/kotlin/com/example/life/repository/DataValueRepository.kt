package com.example.life.repository

import com.example.life.entity.DataValueEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DataValueRepository : CoroutineCrudRepository<DataValueEntity, Int>
