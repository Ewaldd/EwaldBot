package pl.xewald.ewald.bot

import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Game
import pl.xewald.ewald.bot.command.*
import pl.xewald.ewald.bot.command.gamecommand.HiveMCPlayerCommand
import pl.xewald.ewald.bot.command.gamecommand.ServerCommand
import pl.xewald.ewald.bot.command.util.CommandManager
import pl.xewald.ewald.bot.config.EwaldBotConfig
import pl.xewald.ewald.bot.listener.JoinListener
import pl.xewald.ewald.bot.listener.MessageListener
import pl.xewald.ewald.bot.util.EwaldBotException

class EwaldBot(val config: EwaldBotConfig) {

    lateinit var jda: JDA
        private set

    var commandManager = CommandManager()

    var running = false
        private set

    fun start() {
        if (running)
            throw EwaldBotException("Bot is already running!")
        val token = config.token
        if (token.isEmpty() || token == "TOKEN")
            throw EwaldBotException("You have to set token in configuration file!")
        jda = JDABuilder(AccountType.BOT)
                .setToken(token)
                .addEventListener(JoinListener(this))
                .addEventListener(MessageListener(this))
                .setAudioEnabled(false)
                .setAutoReconnect(true)
                .setStatus(OnlineStatus.ONLINE)
                .setGame(Game.streaming("Wpisz !help | v1.1", "https://www.twitch.tv/boleknowak"))
                .buildBlocking()
        commandManager.add(HelpCommand(this))
        commandManager.add(ChannelCommand(this))
        commandManager.add(IllegalCommand(this))
        commandManager.add(UserCommand(this))
        commandManager.add(BotCommand(this))
        commandManager.add(BroadcastCommand(this))
        commandManager.add(ServerCommand(this))
        commandManager.add(HiveMCPlayerCommand(this))
        commandManager.add(IllegalCommand(this))
        commandManager.add(WeatherCommand(this))
        commandManager.add(PollCommand(this))
        running = true
    }

    fun stop() {
        if (!running)
            throw EwaldBotException("Bot is not running!")
        running = false
        jda.shutdown()
    }

}