package rip.sunrise.packets.clientbound

import java.io.Serializable

/**
 * b -> username
 * d -> account session token
 * v -> forum ranks
 * a -> uid
 */
data class LoginResp(val b: String, val d: String, val v: HashSet<Int>, val a: Int) : Serializable
