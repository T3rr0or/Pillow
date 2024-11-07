package rip.sunrise.packets.serverbound

import java.io.Serializable

/**
 * s -> hardware id
 * p -> client version?
 *
 * HARDWARE ID:
 * For Windows: wmic csproduct get UUID
 * For Mac: system_profiler SPHardwareDataType
 * For anything else (linux): /etc/machine-id, /var/lib/dbus/machine-id as a backup
 */
data class RevisionInfoRequest(val s: String, val p: String) : Serializable