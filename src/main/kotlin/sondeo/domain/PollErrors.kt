package sondeo.domain

sealed class PollError : Exception()
class PollCannotBeEmpty : PollError()
class PollIsAlreadyClosed : PollError()
