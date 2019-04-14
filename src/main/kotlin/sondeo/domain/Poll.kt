package sondeo.domain

import java.util.UUID

data class Poll(
        val id: UUID? = null,
        val title: String,
        val location: String
)
