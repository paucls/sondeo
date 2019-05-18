package sondeo.domain

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.UUID

internal class PollTest {

    private val option1 = Option(id = UUID.randomUUID(), text = "option 1")
    private val option2 = Option(id = UUID.randomUUID(), text = "option 2")

    @Test
    internal fun `can create poll`() {
        Poll(
                title = "a title",
                location = "a location",
                options = listOf(
                        Option(text = "option 1")))
    }

    @Test
    internal fun `cannot create poll without options`() {
        Assertions.assertThatThrownBy {
            Poll(
                    title = "a title",
                    location = "a location",
                    options = emptyList())
        }.isInstanceOf(PollCannotBeEmpty::class.java)
    }

    @Test
    internal fun `can close a poll`() {
        val poll = Poll(
                title = "a title",
                location = "a location",
                options = listOf(
                        Option(text = "option 1")))

        poll.close()

        assertThat(poll.state).isEqualTo(PollState.CLOSED)
    }

    @Test
    internal fun `can vote on a poll`() {
        // Given
        val options = listOf(option1, option2)
        val poll = Poll(
                title = "a title",
                location = "a location",
                options = options)

        // When
        val voteCommand = VoteCommand(participant = "John", options = listOf(option1.id!!))
        val vote: Vote = poll.vote(voteCommand)

        // Then
        assertThat(vote.participant).isEqualTo(voteCommand.participant)
        assertThat(vote.options).isEqualTo(voteCommand.options)
        assertThat(vote.date).isBeforeOrEqualTo(LocalDateTime.now())
    }

    @Test
    internal fun `cannot vote on a closed poll`() {
        val options = listOf(option1, option2)
        val poll = Poll(
                title = "a title",
                location = "a location",
                options = options)
        poll.close()
        val voteCommand = VoteCommand(participant = "John", options = listOf(option1.id!!))

        assertThatThrownBy {
            poll.vote(voteCommand)
        }.isInstanceOf(PollIsAlreadyClosed::class.java)
    }
}
