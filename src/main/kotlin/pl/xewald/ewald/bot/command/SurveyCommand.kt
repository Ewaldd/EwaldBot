package pl.xewald.ewald.bot.command

import org.json.*;
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.Main
import pl.xewald.ewald.bot.command.util.Command
import java.awt.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class SurveyCommand(val bot: Main) : Command(
        "ankieta",
        "Stworz ankiete. Przykład: !ankieta <pytanie> TN/AB/ABC/ABCD",
        listOf("none")
) {
    override fun execute(member: Member?, channel: MessageChannel, args: Array<String>){
        if(args.size >=1){

        }

    }
}