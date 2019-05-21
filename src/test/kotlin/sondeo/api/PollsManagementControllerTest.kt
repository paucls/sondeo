package sondeo.api

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.micronaut.http.HttpStatus.CREATED
import io.micronaut.http.HttpStatus.OK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import sondeo.app.PollsService
import sondeo.domain.Option
import sondeo.domain.Poll
import sondeo.domain.PollsRepository
import java.util.UUID

internal class PollsManagementControllerTest {

    private val options = listOf(Option(text = "option 1"), Option(text = "option 2"))

    private val pollsService: PollsService = mock()
    private val pollsRepository: PollsRepository = mock()
    private val pollsController = PollsManagementController(pollsService, pollsRepository)

    @Test
    internal fun `should create a new poll`() {
        val poll = Poll(
                title = "May event topic",
                location = "Cork",
                options = options)

        val response = pollsController.postPoll(poll)

        assertThat(response.status).isEqualTo(CREATED)
        verify(pollsService).createPoll(poll)
    }

    @Test
    internal fun `should delete a poll`() {
        val pollId = UUID.randomUUID()

        val response = pollsController.deletePoll(pollId)

        assertThat(response.status).isEqualTo(OK)
        verify(pollsService).deletePoll(pollId)
    }

    @Test
    internal fun `should return all polls`() {
        val polls = listOf(Poll(options = options), Poll(options = options))
        given(pollsRepository.getAll()).willReturn(polls)

        val response = pollsController.getPolls()

        assertThat(response.status).isEqualTo(OK)
        assertThat(response.body()).isEqualTo(polls)
    }
}
