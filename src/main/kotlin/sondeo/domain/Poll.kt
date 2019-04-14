package sondeo.domain

import java.util.UUID

data class Poll(
        val title: String,
        val location: String
) {
    var id: UUID? = null
}
