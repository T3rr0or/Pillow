package rip.sunrise.packets.serverbound

import java.io.Serializable

/**
 * c -> account session token
 */
data class FreeScriptListRequest(val c: String) : Serializable
