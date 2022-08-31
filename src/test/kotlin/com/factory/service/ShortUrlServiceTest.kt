package com.factory.service

import com.factory.entity.ShortUrlEntity
import com.factory.repository.ShortUrlRepository
import com.factory.service.impl.ShortUrlServiceImpl
import jdk.jfr.Description
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*


class ShortUrlServiceTest {

    @Test
    @Description("Ensure the repository method is called exactly once upon invoking the service method getShortUrlEntity")
    fun whenGetShortUrlEntity_repositorysGetByIdMethodIsCalled() {
        //Given
        val shortUrl = "abc"
        val shortUrlRepository: ShortUrlRepository = Mockito.mock(
            ShortUrlRepository::class.java)
        Mockito.`when`(shortUrlRepository.findById(shortUrl)).thenReturn(Optional.empty())
        val shortUrlService: ShortUrlService = ShortUrlServiceImpl(shortUrlRepository);

        //when
        shortUrlService.getShortUrlEntity(shortUrl)

        //then
        Mockito.verify(shortUrlRepository, Mockito.times(1)).findById(shortUrl)
    }

    @Test
    @Description("Ensure that when repository method getShortUrlEntity returns empty service methods save method is called")
    fun whenGetShortUrlEntityReturnsEmpty_repositorysSaveMethodIsCalled() {
        //Given
        val url = "google.com"
        val shortUrlRepository: ShortUrlRepository = Mockito.mock(
            ShortUrlRepository::class.java)
        val shortUrlService: ShortUrlServiceImpl = ShortUrlServiceImpl(shortUrlRepository);
        val shortUrl = shortUrlService.encodeToID(url)
        val shortUrlEntity = ShortUrlEntity(shortUrl, url)
        Mockito.`when`(shortUrlRepository.findById(shortUrl)).thenReturn(Optional.empty())
        Mockito.`when`(shortUrlRepository.save(shortUrlEntity)).thenReturn(shortUrlEntity)

        //when
        shortUrlService.saveShortUrlEntity(url)

        //then
        Mockito.verify(shortUrlRepository, Mockito.times(1)).findById(shortUrl)
        Mockito.verify(shortUrlRepository, Mockito.times(1)).save(shortUrlEntity)
    }

    @Test
    @Description("Ensure the when repository method getShortUrlEntity returns non-empty, service method save is not called")
    fun whenGetShortUrlEntityReturnsNonEmpty_repositorysSaveMethodIsNotCalled() {
        //Given
        val url = "google.com"
        val shortUrlRepository: ShortUrlRepository = Mockito.mock(
            ShortUrlRepository::class.java)
        val shortUrlService: ShortUrlServiceImpl = ShortUrlServiceImpl(shortUrlRepository);
        val shortUrl = shortUrlService.encodeToID(url)
        val shortUrlEntity = ShortUrlEntity(shortUrl, url)
        Mockito.`when`(shortUrlRepository.findById(shortUrl)).thenReturn(Optional.ofNullable(shortUrlEntity))
        Mockito.`when`(shortUrlRepository.save(shortUrlEntity)).thenReturn(shortUrlEntity)

        //when
        shortUrlService.saveShortUrlEntity(url)

        //then
        Mockito.verify(shortUrlRepository, Mockito.times(1)).findById(shortUrl)
        Mockito.verify(shortUrlRepository, Mockito.never()).save(shortUrlEntity)
    }

}