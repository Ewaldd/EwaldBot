package pl.xewald.ewald.bot.command.useful

import khttp.get
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageChannel
import pl.xewald.ewald.bot.EwaldBot
import pl.xewald.ewald.bot.command.Command
import pl.xewald.ewald.bot.command.CommandCategory
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class CryptocurrencyCommand(val bot: EwaldBot) : Command(
        "kryptowaluta",
        CommandCategory.USEFUL,
        "Sprawdź informacje o kryptowalucie.",
        listOf("cryptocurrency", "krypto")
) {
    override fun execute(member: Member?, channel: MessageChannel, message: Message, args: Array<String>) {
        if (args.size == 1) {
            val obj = get("https://api.coinmarketcap.com/v1/ticker/${args[0]}?convert=PLN").jsonArray
            val pricepln = obj.getJSONObject(0).getBigDecimal("price_pln").setScale(2, BigDecimal.ROUND_HALF_DOWN).toDouble()
            val priceusd = obj.getJSONObject(0).getBigDecimal("price_usd").setScale(2, BigDecimal.ROUND_HALF_DOWN).toDouble()
            val pricebtc = obj.getJSONObject(0).getBigDecimal("price_btc").setScale(8, BigDecimal.ROUND_HALF_DOWN).toDouble()
            val update = Date(obj.getJSONObject(0).getLong("last_updated") * 1000L)
            val sdf = SimpleDateFormat("HH:mm")
            val formattedUpdate = sdf.format(update)
            val eb = EmbedBuilder()
            eb.setTitle("Informacje o: ${obj.getJSONObject(0).getString("name")}")
            eb.setDescription("Skrót: ${obj.getJSONObject(0).getString("symbol")} \nCena: $pricepln PLN" +
                    "\nCena: $priceusd USD\nCena: $pricebtc BTC" +
                    "\nMiejsce w rankingu: ${obj.getJSONObject(0).getInt("rank")}" +
                    "\nOstatnia zmiana: $formattedUpdate" +
                    "\nZmiana w ciągu ostatniej godziny: ${obj.getJSONObject(0).getString("percent_change_1h")} %" +
                    "\nZmiany w ciągu ostatniego dnia: ${obj.getJSONObject(0).getString("percent_change_24h")} %" +
                    "\nZmiany w ciągu ostatniego tygodnia: ${obj.getJSONObject(0).getString("percent_change_7d")} %")
            channel.sendMessage(eb.build()).queue()
        }

    }
}

