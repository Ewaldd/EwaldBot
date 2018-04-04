package pl.xewald.ewald.bot

import pl.xewald.ewald.bot.config.EwaldBotConfig
import pl.xewald.ewald.bot.util.ConfigManager
import java.io.File

fun main(args: Array<String>) {
    val file = File(System.getProperty("user.dir") + File.separator + "EwaldBot", "config.json")
    val config = ConfigManager.loadConfig(file, EwaldBotConfig::class.java)
    val bot = EwaldBot(config)
    bot.start()
}