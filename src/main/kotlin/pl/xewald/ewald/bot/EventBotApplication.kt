package pl.xewald.ewald.bot

import pl.xewald.ewald.bot.config.EventBotConfig


fun main(args: Array<String>) {
    val config = EventBotConfig()
    val bot = EventBot(config)
    bot.start()
}