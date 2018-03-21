package pl.xewald.ewald.bot.listener

import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import pl.xewald.ewald.bot.EwaldBot

class JoinListener(val bot: EwaldBot): ListenerAdapter() {

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {
    }

}