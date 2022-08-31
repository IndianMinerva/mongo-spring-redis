package com.factory.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document
data class ShortUrlEntity(@Id val shortUrl: String, val url: String): Serializable