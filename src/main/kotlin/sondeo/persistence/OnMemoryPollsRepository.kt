package sondeo.persistence

import sondeo.domain.Poll
import sondeo.domain.PollsRepository
import java.util.UUID
import javax.inject.Singleton

//@Singleton
class OnMemoryPollsRepository : PollsRepository {
    private val polls = mutableSetOf<Poll>()

    override fun add(poll: Poll): Poll {
        val newPoll = poll.copy(id = UUID.randomUUID())

        polls.add(newPoll)

        return newPoll
    }

    override fun delete(pollId: UUID) {
        polls.removeIf { it.id == pollId }
    }

    fun getAll(): List<Poll> {
        return polls.toList()
    }
}
