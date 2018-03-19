package pl.xewald.ewald.bot.command.util

import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.MessageChannel


abstract class Command(
        val name: String,
        val description: String,
        val aliases: List<String>
) {

    abstract fun execute(member: Member?, channel: MessageChannel, args: Array<String>)


}