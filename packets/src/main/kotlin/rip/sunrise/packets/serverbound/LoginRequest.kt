package rip.sunrise.packets.serverbound

import java.io.Serializable

/**
 * Login request
 * l -> username
 * x -> password
 * f -> hardware id
 *
 * For Windows: wmic csproduct get UUID
 * For Mac: system_profiler SPHardwareDataType
 * For anything else (linux): /etc/machine-id, /var/lib/dbus/machine-id as a backup
 */
data class LoginRequest(val l: String, val x: String, val f: String) : Serializable
