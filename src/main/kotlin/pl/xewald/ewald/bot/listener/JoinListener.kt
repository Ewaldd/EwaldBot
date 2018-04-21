package pl.xewald.ewald.bot.listener

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import pl.xewald.ewald.bot.EwaldBot
import java.awt.Color

class JoinListener(val bot: EwaldBot) : ListenerAdapter() {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
        event.guild.getTextChannelsByName("general", false).get(0).sendMessage(
                "Witamy ${event.member.asMention} na serwerze ${event.guild.name} \uD83D\uDE09").queue()
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.green)
        eb.setDescription(event.user.asMention + " wszedł na serwer.")
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }
}