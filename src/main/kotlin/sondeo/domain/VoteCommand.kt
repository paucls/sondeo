package sondeo.domain

import java.util.UUID

data class VoteCommand(
        val participant: String,
        val options: List<UUID>)