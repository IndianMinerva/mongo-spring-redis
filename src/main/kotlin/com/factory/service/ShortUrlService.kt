package com.factory.service

import com.factory.entity.ShortUrlEntity
import java.util.*

interface ShortUrlService {

    fun saveShortUrlEntity(url: String): ShortUrlEntity

    fun getShortUrlEntity(id: String): Optional<ShortUrlEntity>

}