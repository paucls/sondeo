package sondeo.persistence

import io.micronaut.spring.tx.annotation.Transactional
import sondeo.domain.Poll
import sondeo.domain.PollsRepository
import java.util.Optional
import java.util.UUID
import javax.inject.Singleton
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Suppress("JpaQlInspection")
@Singleton
open class JpaPollsRepository(
        @PersistenceContext
        private val entityManager: EntityManager
) : PollsRepository {

    @Transactional
    override fun add(poll: Poll): Poll {
        val newPoll = poll.copy(id = UUID.randomUUID())
        entityManager.persist(newPoll)
        return newPoll
    }

    @Transactional
    override fun delete(pollId: UUID) {
        findById(pollId).ifPresent {
            entityManager.remove(it)
        }
    }

    @Transactional
    override fun getAll(): List<Poll> {
        val query = entityManager.createQuery("SELECT p from Poll p")
        return query.resultList as List<Poll>
    }

    private fun findById(pollId: UUID): Optional<Poll> {
        return Optional.ofNullable(
                entityManager.find(Poll::class.java, pollId))
    }
}