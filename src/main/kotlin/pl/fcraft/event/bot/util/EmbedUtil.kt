package pl.fcraft.event.bot.util

import net.dv8tion.jda.core.EmbedBuilder
import java.awt.Color

fun basicEmbedBuilder(title: String): EmbedBuilder {
    return EmbedBuilder()
            .setAuthor(title, null, "https://xewald.pl/Ewald.gif")
            .setColor(Color.getColor("c0392b"))
}