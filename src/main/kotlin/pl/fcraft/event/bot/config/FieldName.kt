package pl.fcraft.event.bot.config

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class FieldName(val name: String)