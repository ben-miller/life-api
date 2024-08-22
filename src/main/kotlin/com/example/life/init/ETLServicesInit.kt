package com.example.life.init

import com.example.life.etl.ETLService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ETLServicesInit(
    private val etlServices: List<ETLService<*>>
) {
    @EventListener(ApplicationReadyEvent::class)
    suspend fun onApplicationReady(event: ApplicationReadyEvent) {
        etlServices.forEach { etlService ->
            etlService.dbInit()
        }
    }
}
