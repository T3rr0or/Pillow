package rip.sunrise.packets.serverbound

import java.io.Serializable


/**
 * Revision info request
 * s -> hardware id
 * p -> client version?
 */
data class RevisionInfoRequest(val s: String, val p: String) : Serializable