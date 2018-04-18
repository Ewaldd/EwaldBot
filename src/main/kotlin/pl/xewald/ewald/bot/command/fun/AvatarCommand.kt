package pl.xewald.ewald.bot.command.`fun`

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import khttp.delete as httpDelete

class AvatarCommand(val bot: EwaldBot) : Command(
        "avatar",
        CommandCategory.FUN,
        "Wyświetla awatar podanej osoby",
        listOf("awatar")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        val user = if (!message.mentionedMembers.isEmpty()) message.mentionedMembers.first() else member
        val eb = EmbedBuilder()
        if(user != member){
            eb.setDescription("Awatar użytkownika ${user!!.effectiveName}: [Klik](${user.user.avatarUrl})")
        }
        else{
            eb.setDescription("Twój awatar: [Klik](${user!!.user.avatarUrl})")
        }
        eb.setImage(user.user.avatarUrl)
        eb.setColor(user.color)
        channel.sendMessage(eb.build()).queue()
    }
}