package rip.sunrise.packets.clientbound

import java.io.Serializable

/**
 * state -> a switch, 0 for OK, 1 for too many clients, 2 for ???
 * c -> script session id
 */
data class ScriptSessionResp(val v: Byte, val c: String?) : Serializable