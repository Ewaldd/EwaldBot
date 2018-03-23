package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.util.Command
import java.awt.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class AdCommand(val bot: EwaldBot) : Command(
        "ogloszenie",
        "Napisz ogłoszenie",
        listOf("pomoc")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        if (member == null) {
            channel.sendMessage("Ta komenda zadziała tylko na serwerach.").queue()
            return
        }
        if (member.hasPermission(Permission.MESSAGE_MANAGE) || member.user.name == "Ewald" || member.user.name == "boleknowak" || member.user.name == "Ixidi") {
            if (args.size == 0) {
                channel.sendMessage("Nie podałeś treści ogłoszenia!").queue()
                return
            } else {
                var ad = ""
                for (arg in args) ad += arg + " "
                val eb = EmbedBuilder()
                eb.setTitle("Ogłoszenie")
                eb.setDescription(ad)
                eb.setColor(Color.RED)
                eb.setFooter("${member.user.name}, $formatted", "${member.user.avatarUrl}")
                channel.sendMessage(eb.build()).queue()
            }
        }else{
            channel.sendMessage("Nie masz uprawnień do tego!").queue()
        }
    }
}