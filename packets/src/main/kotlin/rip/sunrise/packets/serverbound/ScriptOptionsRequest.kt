package rip.sunrise.packets.serverbound

import java.io.Serializable

/**
 * Request to get the script options, just constant values predefined and fetched using ScriptManager.getIntParam
 * v -> account session id
 * b -> script instance session id
 */
data class ScriptOptionsRequest(val v: String, val b: String) : Serializable
