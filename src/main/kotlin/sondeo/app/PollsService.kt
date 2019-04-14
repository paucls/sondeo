package sondeo.app

import sondeo.domain.Poll
import sondeo.domain.PollsRepository
import java.util.UUID
import javax.inject.Singleton

@Singleton
class PollsService(private val pollsRepository: PollsRepository) {

    fun createPoll(poll: Poll): Poll {
        return pollsRepository.add(poll)
    }

    fun deletePoll(pollId: UUID) {
        pollsRepository.delete(pollId)
    }
}
