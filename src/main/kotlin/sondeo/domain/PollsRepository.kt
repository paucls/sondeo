package sondeo.domain

import java.util.UUID

interface PollsRepository {
    fun add(poll: Poll): Poll
    fun delete(pollId: UUID)
    fun getAll(): List<Poll>
}
