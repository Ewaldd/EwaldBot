package pl.xewald.ewald.bot

import pl.xewald.ewald.bot.config.EwaldBotConfig

fun main(args: Array<String>) {
    val config = EwaldBotConfig()
    val bot = EwaldBot(config)
    bot.start()
}