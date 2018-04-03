package pl.xewald.ewald.bot.util

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import java.io.File
import java.nio.charset.StandardCharsets
import java.util.stream.Collectors

object ConfigManager {

    fun <T> loadConfig(file: File, a: Class<T>) : T {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        val adapter = moshi.adapter(a).indent("    ")
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
            val inst = a.newInstance()
            val jsonString = adapter.toJson(inst)
            file.writeText(jsonString, StandardCharsets.UTF_8)
            return inst
        }
        val lines = file.readLines(StandardCharsets.UTF_8)
        val text = lines.stream().collect(Collectors.joining("\n"))
        return adapter.fromJson(text)!!
    }

}