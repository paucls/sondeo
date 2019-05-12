package sondeo.app

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import sondeo.domain.Option
import sondeo.domain.Poll
import sondeo.domain.PollsRepository
import java.util.UUID

internal class PollsServiceTest {

    private val options = listOf(Option(text = "option 1"), Option(text = "option 2"))

    private val pollsRepository: PollsRepository = mock()
    private val pollsService = PollsService(pollsRepository)

    @Test
    internal fun `should save poll on repository`() {
        val poll = Poll(
                title = "May event topic",
                location = "Cork",
                options = options)
        val savedPoll = Poll(
                id = UUID.randomUUID(),
                title = "May event topic",
                location = "Cork",
                options = options)

        given(pollsRepository.add(poll)).willReturn(savedPoll)

        val response = pollsService.createPoll(poll)

        verify(pollsRepository).add(poll)
        assertThat(response).isEqualTo(savedPoll)
    }

    @Test
    internal fun `should delete poll on repository`() {
        val pollId = UUID.randomUUID()

        pollsService.deletePoll(pollId)

        verify(pollsRepository).delete(pollId)
    }
}
