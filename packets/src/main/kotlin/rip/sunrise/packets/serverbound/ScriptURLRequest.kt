package rip.sunrise.packets.serverbound

import java.io.Serializable

/**
 * f -> scriptId
 * o -> account session token
 * e -> script instance token
 */
data class ScriptURLRequest(val f: Int, val o: String, val e: String) : Serializable
