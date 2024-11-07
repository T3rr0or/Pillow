package rip.sunrise.server.config

import com.google.gson.Gson
import rip.sunrise.packets.clientbound.ScriptWrapper
import java.io.File

class Config(private val configFile: File) {
    var revisionData = ""
    var scripts = mutableListOf<Script>()
    var serverUrl = ""

    init {
        assert(configFile.exists())

        load()
    }

    fun load() {
        runCatching {
            val gson = Gson()
            val config = gson.fromJson(configFile.reader(), Config::class.java)

            val revisionFile = File(config.revisionFile)
            if (!revisionFile.isFile) {
                error("Revision file ${revisionFile.absolutePath} isn't a normal file!")
            }

            val scriptConfigDirectory = File(config.scriptConfigDir)
            if (!scriptConfigDirectory.isDirectory) {
                error("Script config directory ${scriptConfigDirectory.absolutePath} is not a directory!")
            }

            scripts.clear()
            scriptConfigDirectory.listFiles()!!.forEachIndexed { index, file ->
                val scriptConfig = Gson().fromJson(file.reader(), ScriptConfig::class.java)

                val scriptJar = File(scriptConfig.jarFile)
                if (!scriptJar.isFile) {
                    error("Script jar ${scriptJar.absolutePath} isn't a normal file!")
                }

                val optionFile = File(scriptConfig.optionFile)
                if (!optionFile.isFile) {
                    error("Option file ${scriptJar.absolutePath} isn't a normal file!")
                }

                val metadata = ScriptWrapper(
                    0,
                    scriptConfig.description,
                    scriptConfig.name,
                    0,
                    scriptConfig.version,
                    "",
                    "",
                    scriptConfig.author,
                    "",
                    scriptConfig.imageUrl,
                    index,
                    index,
                    false
                )

                scripts.add(Script(metadata, scriptJar.readBytes(), optionFile.readLines()))
            }

            this.serverUrl = config.serverUrl
            this.revisionData = revisionFile.readText()
        }.onFailure {
            it.printStackTrace()
        }
    }

    fun getScript(id: Int): Script {
        return scripts.firstOrNull { it.metadata.d == id } ?: error("Couldn't find script with id $id")
    }

    private data class Config(val revisionFile: String, val scriptConfigDir: String, val serverUrl: String)
    class Script(val metadata: ScriptWrapper, val bytes: ByteArray, val options: List<String>)
}