package sondeo.persistence

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import sondeo.domain.Poll

internal class OnMemoryPollsRepositoryTest {

    private val repository = OnMemoryPollsRepository()

    @Test
    internal fun `should add the given poll`() {
        repository.add(Poll(
                title = "a title",
                location = "a location"))

        assertThat(repository.getAll()).isNotEmpty
    }

    @Test
    internal fun `should delete the poll`() {
        val poll = repository.add(Poll(
                title = "a title",
                location = "a location"))

        repository.delete(poll.id!!)

        assertThat(repository.getAll()).isEmpty()
    }
}
