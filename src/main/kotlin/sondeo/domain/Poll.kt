package sondeo.domain

import java.util.UUID
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Poll(
        @Id
        val id: UUID? = null,
        val title: String = "",
        val location: String = "",
        @ElementCollection
        val options: List<Option>
) {
    init {
        if (options.isEmpty()) throw PollCannotBeEmpty()
    }
}
