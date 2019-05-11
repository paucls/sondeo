package sondeo.feature

import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest.DELETE
import io.micronaut.http.HttpRequest.GET
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus.CREATED
import io.micronaut.http.HttpStatus.OK
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import sondeo.domain.Poll
import java.util.UUID


class PollManagementFeatureTest {

    private val embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
    private val client: RxHttpClient = RxHttpClient.create(embeddedServer.url)

    @Test
    internal fun `should create a new poll`() {
        val response = postPoll(Poll(title = "May event topic", location = "Cork"))

        assertThat(response.status).isEqualTo(CREATED)
        val poll = response.body()!!
        assertThat(poll.id).isNotNull()
        assertThat(poll.title).isEqualTo("May event topic")
        assertThat(poll.location).isEqualTo("Cork")
    }

    @Test
    internal fun `should delete existing poll`() {
        val pollId = UUID.randomUUID()

        val response = client.exchange(
                DELETE<Unit>("/polls/$pollId"),
                Poll::class.java).blockingFirst()

        assertThat(response.status).isEqualTo(OK)
    }

    @Test
    internal fun `should list existing polls`() {
        postPoll(Poll(title = "May event venue", location = "Cork"))
        postPoll(Poll(title = "June event topic", location = "Cork"))

        val response = client.exchange(
                GET<List<Poll>>("/polls"),
                Argument.of(List::class.java, Poll::class.java)).blockingFirst()

        assertThat(response.status).isEqualTo(OK)
        val polls = response.body()!!
        assertThat(polls).hasSize(2)
        assertThat((polls[0] as Poll).title).isEqualTo("May event venue")
        assertThat((polls[1] as Poll).title).isEqualTo("June event topic")
    }

    private fun postPoll(poll: Poll): HttpResponse<Poll> {
        return client.exchange(
                POST("/polls", poll),
                Poll::class.java).blockingFirst()
    }

    @AfterEach
    internal fun tearDown() {
        client.close()
        embeddedServer.close()
    }
}
