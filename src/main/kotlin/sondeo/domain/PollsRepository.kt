package sondeo.domain

interface PollsRepository {
    fun add(poll: Poll): Poll
}
