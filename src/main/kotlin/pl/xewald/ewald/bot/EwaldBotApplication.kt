package pl.xewald.ewald.bot

import jdk.jfr.Event
import pl.xewald.ewald.bot.config.EwaldBotConfig

fun main(args: Array<String>) {
    val config = EwaldBotConfig()
    val bot = Main(config)
    bot.start()
}