package example.koin

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.tomcat.EngineMain.main(args)
}

fun Application.module() {
    settingKoin()
    configureRouting()
}
