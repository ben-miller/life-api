package com.example.life.repository

import com.example.life.entity.DataPointEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DataPointRepository : CoroutineCrudRepository<DataPointEntity, Int>
