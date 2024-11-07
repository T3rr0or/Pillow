package rip.sunrise.packets.clientbound

import java.io.Serializable

/**
 * w -> url
 * g -> trial time in seconds, <= 0 or above 1d is forever
 */
data class ScriptURLResp(val w: String, val g: Int) : Serializable
