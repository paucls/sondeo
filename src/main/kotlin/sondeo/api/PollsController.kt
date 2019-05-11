package sondeo.api

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.created
import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import sondeo.app.PollsService
import sondeo.domain.Poll
import sondeo.domain.PollsRepository
import java.util.UUID
import javax.validation.Valid

@Controller("/polls")
class PollsController(private val pollsService: PollsService,
                      private val pollsRepository: PollsRepository) {

    @Post
    fun postPoll(@Valid @Body poll: Poll): HttpResponse<Poll> {
        return created(pollsService.createPoll(poll))
    }

    @Delete("/{pollId}")
    fun deletePoll(pollId: UUID): HttpResponse<Unit> {
        return ok(pollsService.deletePoll(pollId))
    }

    @Get
    fun getPolls(): HttpResponse<List<Poll>> {
        return ok(pollsRepository.getAll())
    }
}
