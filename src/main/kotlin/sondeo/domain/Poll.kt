package sondeo.domain

import java.util.UUID
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Poll(
        @Id
        @GeneratedValue(generator = "system-uuid")
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

    fun vote(c: VoteCommand): Vote {
        if (state == PollState.CLOSED)
            throw PollIsAlreadyClosed()

        return Vote(participant = c.participant, options = c.options)
    }
}

enum class PollState {
    OPEN,
    CLOSED
}
