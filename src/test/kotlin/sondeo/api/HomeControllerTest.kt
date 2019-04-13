package sondeo.api

import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HomeControllerTest {

    private val embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
    private val client: HttpClient = HttpClient.create(embeddedServer.url)

    @Test
    internal fun `should say welcome`() {
        val response: String = client.toBlocking().retrieve("/")
        assertEquals(response, "Welcome to Sondeo!")
    }

    @AfterEach
    internal fun tearDown() {
        client.close()
        embeddedServer.close()
    }
}
