package rip.sunrise.packets.serverbound

import java.io.Serializable

/**
 * g -> `${accountToken}:MID:${hardwareId}`
 */
data class ScriptSessionRequest(val g: String) : Serializable
