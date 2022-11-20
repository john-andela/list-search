package com.johnseremba.stubhub.model

import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class Event(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val city: String,
    val venue: String,
    val price: BigDecimal,
    val date: LocalDate
)
