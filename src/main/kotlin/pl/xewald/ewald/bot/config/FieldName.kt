package pl.xewald.ewald.bot.config

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class FieldName(val name: String)