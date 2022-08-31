package com.factory

import com.factory.service.impl.ShortUrlRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = [ShortUrlRepository::class])
@EnableCaching
class ShortyApplication

fun main(args: Array<String>) {
    runApplication<ShortyApplication>(*args)
}