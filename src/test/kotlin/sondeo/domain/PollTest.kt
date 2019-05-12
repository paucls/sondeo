package sondeo.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class PollTest {
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
}
