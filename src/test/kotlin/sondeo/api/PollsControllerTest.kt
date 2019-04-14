package sondeo.api

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import sondeo.app.PollsService
import sondeo.domain.Poll
import java.util.UUID

internal class PollsControllerTest {

    private val pollsService: PollsService = mock()
    private val pollsController = PollsController(pollsService)

    @Test
    internal fun `should tell service to create poll`() {
        val poll = Poll(
                title = "May event topic",
                location = "Cork")

        pollsController.postPoll(poll)

        verify(pollsService).createPoll(poll)
    }

    @Test
    internal fun `should tell service to delete poll`() {
        val pollId = UUID.randomUUID()

        pollsController.deletePoll(pollId)

        verify(pollsService).deletePoll(pollId)
    }
}
