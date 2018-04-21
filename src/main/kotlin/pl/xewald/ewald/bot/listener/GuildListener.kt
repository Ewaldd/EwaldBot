package pl.xewald.ewald.bot.listener

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.channel.category.CategoryCreateEvent
import net.dv8tion.jda.core.events.channel.category.CategoryDeleteEvent
import net.dv8tion.jda.core.events.channel.category.update.CategoryUpdatePermissionsEvent
import net.dv8tion.jda.core.events.channel.category.update.CategoryUpdatePositionEvent
import net.dv8tion.jda.core.events.guild.GuildJoinEvent
import net.dv8tion.jda.core.events.guild.GuildLeaveEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberNickChangeEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleAddEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleRemoveEvent
import net.dv8tion.jda.core.events.guild.update.*
import net.dv8tion.jda.core.events.message.guild.GuildMessageUpdateEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import pl.xewald.ewald.bot.EwaldBot
import java.awt.Color

class GuildListener(val bot: EwaldBot) : ListenerAdapter() {
    override fun onGuildJoin(event: GuildJoinEvent) {
        event.guild.getTextChannelsByName("general", true)[0].sendMessage("Dzień dobry! ;) " +
                "Nie umiesz mnie obsługiwać? https://discordapp.com/invite/D924rf9 <- tu otrzymasz pomoc! Miłego dnia!").queue()
    }

    override fun onGuildMemberRoleAdd(event: GuildMemberRoleAddEvent) {
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.green)
        eb.setDescription("Użytkownik" + event.member.asMention + " otrzymał nową rolę." +
                "\nWszystkie role użytkownika: " + event.member.roles.joinToString(", o"))
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }

    override fun onGuildMemberNickChange(event: GuildMemberNickChangeEvent) {
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.YELLOW)
        eb.setDescription(event.prevNick + " zmienił nick na: " + event.member.effectiveName)
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }

    override fun onGuildMemberRoleRemove(event: GuildMemberRoleRemoveEvent) {
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.red)
        eb.setDescription("Użytkownik" + event.member.asMention + " stracił rolę." +
                "\nWszystkie role użytkownika: " + event.member.roles.joinToString(", o"))
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }

    override fun onCategoryCreate(event: CategoryCreateEvent) {
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.green)
        eb.setDescription("Kategoria: " + event.category.name + "została stworzona!")
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }

    override fun onCategoryDelete(event: CategoryDeleteEvent) {
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.red)
        eb.setDescription("Kategoria: " + event.category.name + " została usunięta!")
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }

    override fun onGuildUpdateAfkChannel(event: GuildUpdateAfkChannelEvent) {
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.yellow)
        eb.setDescription("Kanał afk został zmieniony z:" + event.oldAfkChannel.name + "na:" + event.guild.afkChannel.name)
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }

    override fun onGuildUpdateName(event: GuildUpdateNameEvent) {
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.yellow)
        eb.setDescription("Nazwa serwera została zmieniona z: " + event.oldName + " na: " + event.guild.name)
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }

    override fun onGuildUpdateOwner(event: GuildUpdateOwnerEvent) {
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.yellow)
        eb.setDescription("Właściciel serwera został zmieniony z: " + event.oldOwner.asMention + " na: " + event.guild.owner.asMention)
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }

    override fun onGuildUpdateRegion(event: GuildUpdateRegionEvent) {
        val eb = EmbedBuilder()
        eb.setTitle("Logi")
        eb.setColor(Color.yellow)
        eb.setDescription("Region serwera został zmieniony z: " + event.oldRegion + " na: " + event.newRegion)
        event.guild.getTextChannelsByName("logi", true)[0].sendMessage(eb.build()).queue()
    }
}
