package sondeo.domain

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Vote(
        @Id
        val id: UUID? = null,
        val participant: String,
        @ElementCollection
        val options: List<UUID>
) {
    val date: LocalDateTime = LocalDateTime.now()
}