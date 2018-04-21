package pl.xewald.ewald.bot.listener

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.guild.GuildBanEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import pl.xewald.ewald.bot.EwaldBot
import java.awt.Color

class BanListener(val bot: EwaldBot): ListenerAdapter(){
    override fun onGuildBan(event: GuildBanEvent) {
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.RED)
        eb.setDescription(event.user.asMention+ " został zbanowany!")
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }
}