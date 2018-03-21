package pl.xewald.ewald.bot.listener

import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import pl.xewald.ewald.bot.Main

class JoinListener(val bot: Main): ListenerAdapter() {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
    }

}