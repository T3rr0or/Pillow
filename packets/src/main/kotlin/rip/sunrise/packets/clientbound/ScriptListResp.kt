package rip.sunrise.packets.clientbound

import java.io.Serializable

/**
 * v -> scripts
 */
data class ScriptListResp(val v: Collection<ScriptWrapper>) : Serializable
