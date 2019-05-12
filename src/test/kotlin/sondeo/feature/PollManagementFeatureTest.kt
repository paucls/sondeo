package sondeo.feature

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.restassured.RestAssured.delete
import io.restassured.RestAssured.get
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.ResponseBodyExtractionOptions
import io.restassured.specification.RequestSpecification
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.assertj.core.groups.Tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import sondeo.domain.Poll

class PollManagementFeatureTest {

    private val embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)

    @Test
    internal fun `should create a new poll`() {
        val poll = createPoll(Poll(title = "May event topic", location = "Cork"))

        assertThat(poll.id).isNotNull()
        assertThat(poll.title).isEqualTo("May event topic")
        assertThat(poll.location).isEqualTo("Cork")
    }

    @Test
    internal fun `should delete existing poll`() {
        val poll = createPoll(Poll(title = "May event venue", location = "Cork"))

        delete("${embeddedServer.url}/polls/${poll.id}")
                .then()
                .statusCode(200)
    }

    @Test
    internal fun `should list all existing polls`() {
        createPoll(Poll(title = "May event venue", location = "Cork"))
        createPoll(Poll(title = "June event topic", location = "Cork"))

        val polls = get("${embeddedServer.url}/polls")
                .then()
                .statusCode(200)
                .extract().to<List<Poll>>()

        assertThat(polls).extracting("title", "location").containsExactly(
                tuple("May event venue", "Cork"),
                tuple("June event topic", "Cork")
        )
    }

    private fun createPoll(poll: Poll): Poll {
        return given().request()
                .body(poll)
                .contentType(ContentType.JSON)
                .whenever()
                .post("${embeddedServer.url}/polls")
                .then()
                .statusCode(201)
                .extract().to()
    }

    @AfterEach
    internal fun tearDown() {
        embeddedServer.close()
    }
}

fun RequestSpecification.whenever(): RequestSpecification {
    return this.`when`()
}

inline fun <reified T> ResponseBodyExtractionOptions.to(): T {
    return this.`as`(T::class.java)
}
