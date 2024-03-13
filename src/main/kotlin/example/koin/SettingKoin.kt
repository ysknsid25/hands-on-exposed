package example.koin

import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.settingKoin() {
    install(Koin){
        modules(Module.koinPracticeModules)
    }
}