package pl.fcraft.event.bot

import pl.fcraft.event.bot.config.EventBotConfig


fun main(args: Array<String>) {
    val config = EventBotConfig()
    val bot = EventBot(config)
            bot.start()
}