package pl.xewald.ewald.bot.command.`fun`

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.util.*

class HitCommand(val bot: EwaldBot) : Command(
        "uderz",
        CommandCategory.FUN,
        "Uderz kogoś/coś",
        listOf("hit")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (member == null) {
            channel.sendMessage("Ta komenda zadziała tylko na serwerach!").queue()
            return
        }
        if (args.isNotEmpty()) {
            if (message.mentionedUsers.isEmpty()) {
                val eb = EmbedBuilder()
                eb.setColor(member.color)
                eb.setTitle("${member.effectiveName} uderza ${args.joinToString(" ")} \uD83D\uDC4A")
                val rand = Random().nextInt(11)
                eb.setImage(
                        when (rand) {
                            1 -> "https://media.giphy.com/media/BKRECiW08vdjG/giphy.gif"
                            2 -> "https://media.giphy.com/media/AlsIdbTgxX0LC/giphy.gif"
                            3 -> "https://media1.tenor.com/images/8c9cd36598f039fe62576e7d8b2de4a8/tenor.gif?itemid=5483839"
                            4 -> "https://media1.tenor.com/images/57f6c31dca1cce8250c6317208fa1a67/tenor.gif?itemid=4735568"
                            5 -> "https://media1.tenor.com/images/23f06ab6deb735dc3e9d07285a5e6036/tenor.gif?itemid=10235547"
                            6 -> "https://media1.tenor.com/images/6d3a4d9c4f74609ce9ecc1da988b81f3/tenor.gif?itemid=5223494"
                            7 -> "https://media1.tenor.com/images/da38350ab750dc6e74c2e4da39f0cfc7/tenor.gif?itemid=4981744"
                            8 -> "https://media1.tenor.com/images/b2853d7fd46fac304fb4fe90fa8dbadb/tenor.gif?itemid=9275135"
                            9 -> "https://media1.tenor.com/images/bd550dd05407ed19ed799059f1d6e55a/tenor.gif?itemid=10815769"
                            else -> "https://media.giphy.com/media/rcRwO8GMSfNV6/giphy.gif"
                        }
                )
                channel.sendMessage(eb.build()).queue()
            } else {
                channel.sendMessage("Komenda nie może posiadać oznaczenia!").queue()
            }
        } else {
            channel.sendMessage("Wiadomość nie możę być pusta!").queue()
        }
    }
}