package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.util.Command
import pl.xewald.ewald.bot.util.basicEmbedBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class SurveyCommand(val bot: EwaldBot) : Command(
        "ankieta",
        "Stworz ankiete. Przykład: !ankieta <pytanie> *reakcja1-opcja1,reakcja2-opcja2...*",
        emptyList()
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>){
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = current.format(formatter)
        if (member == null) {
            channel.sendMessage("Ta komenda dostepna jest tylko na serwerach!").queue()
            return
        }
        if (args.size < 2) {
            val message = "${member.asMention}, poprawne użycie `!ankieta <pytanie> |reakcja1-opcja1,reakcja2-opcja2...|`"
            channel.sendMessage(message).queue()
            return
        }
        val titleBuilder = StringBuilder()
        val options = HashMap<String, String>()
        for (arg in args) {
            if (arg.startsWith('|') && arg.endsWith('|')) {
                val map = parseReactions(arg)
                for ((reaction, desc) in map) {
                    options[reaction] = desc
                }
            } else {
                titleBuilder.append(arg).append(' ')
            }
        }
        val bodyBuilder = StringBuilder()
        for ((reaction, option) in options) {
            bodyBuilder.append(reaction).append(" - ").append(option).append('\n')
        }
        val eb = EmbedBuilder()
        eb.setTitle("Ankieta")
        eb.addField("${titleBuilder.toString()}", "${bodyBuilder.toString()}", false)
        eb.setAuthor("EwaldBot", "https://xewald.pl/", "https://xewald.pl/Ewald.gif")
        eb.setFooter("Data i godzina: $formatted", "https://xewald.pl/Ewald.gif")
        val mess = channel.sendMessage(eb.build()).complete()
        for (reaction in options.values) {
            mess.addReaction(reaction)
        }
    }

    private fun parseReactions(reactionString: String): HashMap<String, String> {
        val text = reactionString.replace("|", "")
        val splited = text.split(',')
        val map = HashMap<String, String>()
        for (s in splited) {
            val reactionSplited = s.split('-')
            val reaction = reactionSplited[0]
            val desc = reactionSplited[1]
            map[reaction] = desc
        }
        return map
    }
}