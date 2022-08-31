package com.factory.controller

import com.factory.dto.UrlInput
import com.factory.entity.ShortUrlEntity
import com.factory.service.ShortUrlService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class ShortUrlController @Autowired constructor(private val shortUrlService: ShortUrlService) {

    @PostMapping
    fun createShortUrl(@RequestBody urlInput: UrlInput): ResponseEntity<ShortUrlEntity> {
        return ResponseEntity(shortUrlService.saveShortUrlEntity(urlInput.url), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getShortUrl(@PathVariable id: String): ResponseEntity<ShortUrlEntity> {
        return shortUrlService.getShortUrlEntity(id)
            .map { shortUrlEntity -> ResponseEntity(shortUrlEntity, HttpStatus.OK) }
            .orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    }
}
