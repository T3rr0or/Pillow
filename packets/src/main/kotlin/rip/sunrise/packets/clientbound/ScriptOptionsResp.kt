package rip.sunrise.packets.clientbound

import java.io.Serializable

/**
 * c -> scriptOptions, key=value comma separated
 */
data class ScriptOptionsResp(val c: String) : Serializable
