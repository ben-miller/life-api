package com.example.life.repository

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime
import org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(SpringExtension::class)
@DataR2dbcTest
class RepositoryTests {

    @Autowired
    private lateinit var dataSourceRepository: DataSourceRepository

    @Autowired
    private lateinit var dataPointRepository: DataPointRepository

    @Autowired
    private lateinit var dataValueRepository: DataValueRepository

    @BeforeEach
    fun setUp() = runTest {
        dataValueRepository.deleteAll()
        dataPointRepository.deleteAll()
        dataSourceRepository.deleteAll()
    }

    @AfterEach
    fun tearDown() = runTest {
        dataValueRepository.deleteAll()
        dataPointRepository.deleteAll()
        dataSourceRepository.deleteAll()
    }

    @Test
    fun `do stuff`() = runTest {
        val dataSource = DataSourceEntity(sourceName = "Test Source", lastUpdated = LocalDateTime.now())
        val savedDataSource = dataSourceRepository.save(dataSource)

        val retrievedDataSource = dataSourceRepository.findById(savedDataSource.sourceId!!)
        assertEquals(savedDataSource.sourceId, retrievedDataSource?.sourceId)
        assertEquals("Test Source", retrievedDataSource?.sourceName)
    }
}
