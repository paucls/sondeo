package sondeo.feature

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import sondeo.domain.Poll

class CreatePollFeatureTest {

    private val embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
    private val client: RxHttpClient = RxHttpClient.create(embeddedServer.url)

    @Disabled
    @Test
    internal fun `should create a new poll`() {
        val response = client.exchange(
                POST("/polls", Poll("May event topic", "Cork")),
                Poll::class.java).blockingFirst()

        assertThat(response.status).isEqualTo(201)
        val poll = response.body()
        assertThat(poll?.id).isNotNull()
    }

    @AfterEach
    internal fun tearDown() {
        client.close()
        embeddedServer.close()
    }
}
