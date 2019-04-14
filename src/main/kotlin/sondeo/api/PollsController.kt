package sondeo.api

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import sondeo.app.PollsService
import sondeo.domain.Poll
import javax.validation.Valid

@Controller("/polls")
class PollsController(private val pollsService: PollsService) {

    @Post
    fun postPoll(@Valid @Body poll: Poll): HttpResponse<Poll> {
        return HttpResponse.created(
                pollsService.createPoll(poll))
    }
}
