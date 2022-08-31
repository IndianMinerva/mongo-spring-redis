package com.factory.service.impl

import com.factory.entity.ShortUrlEntity
import com.factory.service.ShortUrlService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

@Service
class ShortUrlServiceImpl @Autowired constructor(private val shortUrlRepository: ShortUrlRepository) : ShortUrlService {

    override fun saveShortUrlEntity(url: String): ShortUrlEntity {
        val shortUrl = encodeToID(url = url);
        val maybeShortUrlEntity: Optional<ShortUrlEntity> = shortUrlRepository.findById(shortUrl)
        return if (maybeShortUrlEntity.isPresent) {
            maybeShortUrlEntity.get()
        } else {
            shortUrlRepository.save(ShortUrlEntity(url = url, shortUrl = shortUrl))
        }
        //.map { shortUrlEntity -> shortUrlEntity }
        //    .orElse(return shortUrlRepository.save(ShortUrlEntity(url = url, shortUrl = shortUrl)))

    }

    @Cacheable(value = ["urlsCache"], key = "#id")
    override fun getShortUrlEntity(id: String): Optional<ShortUrlEntity> {
        return shortUrlRepository.findById(id)
    }

    fun encodeToID(url: String): String {
        val hashBytes = MessageDigest.getInstance("MD5").digest(url.toByteArray(Charsets.UTF_8)) //TODO: Hash-collision
        val hashString = String.format("%032x", BigInteger(1, hashBytes))
        return hashString.take(10)
    }
}

interface ShortUrlRepository : MongoRepository<ShortUrlEntity, String?>