package pl.xewald.ewald.bot.command

import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel


abstract class Command(
        val name: String,
        val category: CommandCategory,
        val description: String,
        val aliases: List<String>
) {

    abstract fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>)

}