package sondeo.app

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import sondeo.domain.Poll
import sondeo.domain.PollsRepository

internal class PollsServiceTest {

    private val pollsRepository: PollsRepository = mock()
    private val pollsService = PollsService(pollsRepository)

    @Test
    internal fun `should save poll on repository`() {
        val poll = Poll("May event topic", "Cork")
        val savedPoll = Poll("May event topic", "Cork")

        given(pollsRepository.add(poll)).willReturn(savedPoll)

        val response = pollsService.createPoll(poll)

        verify(pollsRepository).add(poll)
        assertThat(response).isEqualTo(savedPoll)
    }
}
