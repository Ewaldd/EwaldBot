package pl.fcraft.event.bot.util

class EventBotException: RuntimeException {

    constructor() : super()

    constructor(p0: String?) : super(p0)

    constructor(p0: Throwable?) : super(p0)
}