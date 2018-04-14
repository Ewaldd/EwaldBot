package pl.xewald.ewald.bot.command.admin

import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.awt.Color
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class BroadcastCommand(val bot: EwaldBot) : Command(
        "ogloszenie",
        CommandCategory.ADMIN,
        "Napisz ogłoszenie",
        listOf("ogłoszenie", "bc", "broadcast")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
        val formatted = current.format(formatter)
        if (member == null) {
            channel.sendMessage("Ta komenda zadziała tylko na serwerach.").queue()
            return
        }
        if (member.hasPermission(Permission.MESSAGE_MANAGE) || member.user.name == "Ewald") {
            if (args.isEmpty()) {
                channel.sendMessage("Nie podałeś treści ogłoszenia!").queue()
                return
            } else {
                val eb = EmbedBuilder()
                eb.setTitle("Ogłoszenie")
                eb.setDescription(args.joinToString(" "))
                eb.setColor(Color.RED)
                eb.setFooter("${member.user.name}, $formatted", member.user.avatarUrl)
                channel.deleteMessageById(message.id).queue()
                channel.sendMessage(eb.build()).queue({ success ->
                    run {
                        if (!success.member.hasPermission(success.textChannel, listOf(Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_HISTORY))) {
                            return@queue
                        }
                        success.addReaction("\uD83D\uDC4D").queue(
                                { _ ->
                                    success.addReaction("❤").queue(
                                            { _ ->
                                                success.addReaction("\uD83D\uDE2D").queue(
                                                        { _ -> success.addReaction("\uD83E\uDD80").queue() }
                                                )
                                            }
                                    )
                                }
                        )
                    }
                })
            }
        } else {
            channel.sendMessage("Nie masz uprawnień do tego!").queue()
        }
    }
}