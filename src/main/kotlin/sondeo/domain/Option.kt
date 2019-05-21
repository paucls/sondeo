package sondeo.domain

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Option(
        @Id
        @GeneratedValue(generator = "system-uuid")
        val id: UUID? = null,
        val text: String
)
