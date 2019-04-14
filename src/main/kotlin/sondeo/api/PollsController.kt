package sondeo.api

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/polls")
class PollsController {

    @Post
    fun postPoll() {

    }
}
