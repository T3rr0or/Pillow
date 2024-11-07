package rip.sunrise.packets.clientbound

import java.io.Serializable

/**
 * Response for starting local scripts.
 * r -> whether the script should stop
 */
data class ScriptStartResp(val r: Boolean) : Serializable
