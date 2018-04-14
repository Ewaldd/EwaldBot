package pl.xewald.ewald.bot.command.`fun`

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import net.dv8tion.jda.core.utils.WidgetUtil
import org.json.JSONObject
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.awt.Color
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import java.math.*

class RPSCommand(val bot: EwaldBot) : Command(
        "kpn",
        CommandCategory.FUN,
        "Zagraj z botem w kamień papier nożyce.",
        listOf("rpn")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (args.size == 1) {
            if (args[0].toLowerCase() == "kamien" || args[0].toLowerCase() == "kamień" || args[0].toLowerCase() == "nożyce" || args[0].toLowerCase() == "nozyce" || args[0].toLowerCase() == "papier") {
                val rand = Random().nextInt(3 - 1 + 1) + 1
                val eb = EmbedBuilder()
                eb.setAuthor("${member!!.effectiveName} wybrał ${args[0]}", null, member.user.avatarUrl)

                when (args[0].toLowerCase()) {
                    "kamień", "kamien" -> {
                        when (rand) {
                            1 -> {
                                eb.setColor(Color.GREEN)
                                eb.setDescription("Brawo! Wygrałeś!\n" +
                                        "Bot wybrał ✌!")
                            }
                            2 -> {
                                eb.setColor(Color.YELLOW)
                                eb.setDescription("Remis! \n" +
                                        "Bot wybrał \uD83E\uDD1C!")
                            }
                            else -> {
                                eb.setColor(Color.RED)
                                eb.setDescription("Tym razem przegrałeś! \n" +
                                        "Bot wybrał \uD83E\uDD1A")
                            }
                        }
                    }
                    "nożyce", "nozyce" -> {
                        when (rand) {
                            1 -> {
                                eb.setColor(Color.YELLOW)
                                eb.setDescription("Remis!\n" +
                                        "Bot wybrał ✌!")
                            }
                            2 -> {
                                eb.setColor(Color.GREEN)
                                eb.setDescription("Brawo! Wygrałeś! \n" +
                                        "Bot wybrał \uD83E\uDD1A!")
                            }
                            else -> {
                                eb.setColor(Color.RED)
                                eb.setDescription("Tym razem przegrałeś! \n" +
                                        "Bot wybrał \uD83E\uDD1C")
                            }
                        }
                    }
                    "papier" -> {
                        when (rand) {
                            1 -> {
                                eb.setColor(Color.RED)
                                eb.setDescription("Tym razem przegrałeś!\n" +
                                        "Bot wybrał ✌!")
                            }
                            2 -> {
                                eb.setColor(Color.GREEN)
                                eb.setDescription("Brawo! Wygrałeś! \n" +
                                        "Bot wybrał \uD83E\uDD1C!")
                            }
                            else -> {
                                eb.setColor(Color.YELLOW)
                                eb.setDescription("Remis! \n" +
                                        "Bot wybrał \uD83E\uDD1A")
                            }
                        }
                    }
                }
                channel.sendMessage(eb.build()).queue()
            }else{
                channel.sendMessage("Wybierz kamień/papier/nożyce").queue()
            }
        }else{
            channel.sendMessage("Wybierz kamień/papier/nożyce").queue()
        }
    }
}


