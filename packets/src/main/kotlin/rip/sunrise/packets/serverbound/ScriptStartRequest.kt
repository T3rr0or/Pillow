package rip.sunrise.packets.serverbound

import java.io.Serializable

/**
 * Sent only for local script start?
 * Remote scripts contain enough metadata to skip this.
 *
 * b -> account session token
 * r -> nonce from a9
 * l -> "%s,%s" on Main class SimpleName and declared method length
 * p -> script data (name, author, category, version, main class, ???, ???, ???)
 * t -> Some value from Boot, looks like encoded jar path (always user.home/DreamBot/BotData/client.jar)
 * q -> ???
 */
data class ScriptStartRequest(
    val b: String,
    val r: String,
    val l: String,
    val p: String,
    val t: String,
    val q: String
) : Serializable
