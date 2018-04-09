package pl.xewald.ewald.bot

import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDA
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.OnlineStatus
import net.dv8tion.jda.core.entities.Game
import pl.xewald.ewald.bot.command.`fun`.IllegalCommand
import pl.xewald.ewald.bot.command.admin.BroadcastCommand
import pl.xewald.ewald.bot.command.admin.PollCommand
import pl.xewald.ewald.bot.command.bot.BotCommand
import pl.xewald.ewald.bot.command.bot.HelpCommand
import pl.xewald.ewald.bot.command.game.HiveMCPlayerCommand
import pl.xewald.ewald.bot.command.game.MCServerCommand
import pl.xewald.ewald.bot.command.CommandManager
import pl.xewald.ewald.bot.command.`fun`.EmbedCommand
import pl.xewald.ewald.bot.command.`fun`.YesNoCommand
import pl.xewald.ewald.bot.command.useful.*
import pl.xewald.ewald.bot.command.useful.ChannelCommand
import pl.xewald.ewald.bot.command.useful.UserCommand
import pl.xewald.ewald.bot.command.useful.WeatherCommand
import pl.xewald.ewald.bot.command.useful.CryptocurrencyCommand
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
                .setGame(Game.streaming("Wpisz !help | v1.2", "https://www.twitch.tv/boleknowak"))
                .buildBlocking()
        commandManager.add(HelpCommand(this))
        commandManager.add(ChannelCommand(this))
        commandManager.add(IllegalCommand(this))
        commandManager.add(UserCommand(this))
        commandManager.add(BotCommand(this))
        commandManager.add(ServerCommand(this))
        commandManager.add(BroadcastCommand(this))
        commandManager.add(MCServerCommand(this))
        commandManager.add(HiveMCPlayerCommand(this))
        commandManager.add(IllegalCommand(this))
        commandManager.add(YesNoCommand(this))
        commandManager.add(EmbedCommand(this))
        commandManager.add(WeatherCommand(this))
        commandManager.add(PollCommand(this))
        commandManager.add(CryptocurrencyCommand(this))
        running = true
    }

    fun stop() {
        if (!running)
            throw EwaldBotException("Bot is not running!")
        running = false
        jda.shutdown()
    }

}