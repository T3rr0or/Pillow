package rip.sunrise.packets.serverbound

import java.io.Serializable

/**
 * c -> account session token
 */
data class PaidScriptListRequest(val c: String) : Serializable
