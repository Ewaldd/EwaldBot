package pl.xewald.ewald.bot.listener

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.guild.GuildBanEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import pl.xewald.ewald.bot.EwaldBot
import java.awt.Color

class LeaveListener(val bot: EwaldBot) : ListenerAdapter() {
    override fun onGuildMemberLeave(event: GuildMemberLeaveEvent) {
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.red)
        eb.setDescription(event.user.asMention + " wyszedł z serwera.")
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }

}