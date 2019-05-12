package sondeo.domain

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Option(
        @Id
        var id: UUID? = null,
        val text: String
)
