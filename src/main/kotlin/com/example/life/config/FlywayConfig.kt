package com.example.life.config

import org.flywaydb.core.Flyway
import org.springframework.boot.autoconfigure.flyway.FlywayProperties
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FlywayConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.flyway")
    fun flywayProperties() = FlywayProperties()

    @Bean(initMethod = "migrate")
    fun flyway(flywayProperties: FlywayProperties, r2dbcProperties: R2dbcProperties): Flyway {
        return Flyway.configure()
            .dataSource(flywayProperties.url, r2dbcProperties.username, r2dbcProperties.password)
            .locations(*flywayProperties.locations.toTypedArray())
            .baselineOnMigrate(true)
            .load()
    }
}
