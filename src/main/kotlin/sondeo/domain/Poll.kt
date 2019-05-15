package sondeo.domain

import java.util.UUID
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id

@Entity
data class Poll(
        @Id
        val id: UUID? = null,
        val title: String = "",
        val location: String = "",
        @ElementCollection(fetch = FetchType.EAGER)
        val options: List<Option>
) {
    var state: PollState = PollState.OPEN

    init {
        if (options.isEmpty()) throw PollCannotBeEmpty()
    }

    fun close() {
        state = PollState.CLOSED
    }
}

enum class PollState {
    OPEN,
    CLOSED
}
