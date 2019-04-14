package sondeo.feature

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import sondeo.domain.Poll

class CreatePollFeatureTest {

    private val embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
    private val client: RxHttpClient = RxHttpClient.create(embeddedServer.url)

    @Test
    internal fun `should create a new poll`() {
        val response = client.exchange(
                POST("/polls", Poll(
                        title = "May event topic",
                        location = "Cork")),
                Poll::class.java).blockingFirst()

        assertThat(response.status).isEqualTo(HttpStatus.CREATED)
        val poll = response.body()!!
        assertThat(poll.id).isNotNull()
        assertThat(poll.title).isEqualTo("May event topic")
        assertThat(poll.location).isEqualTo("Cork")
    }

    @AfterEach
    internal fun tearDown() {
        client.close()
        embeddedServer.close()
    }
}
