package example.koin

import example.koin.accessor.DeleteDataAccessor
import example.koin.accessor.InsertDataAccessor
import example.koin.accessor.SelectDataAccessor
import example.koin.accessor.UpdateDataAccessor
import example.koin.controller.ExposedController
import example.koin.service.DeleteService
import example.koin.service.InsertService
import example.koin.service.SelectService
import example.koin.service.UpdateService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

object Module {
    val koinPracticeModules = module {
        //Controller
        singleOf(::ExposedController)
        //Service
        singleOf(::SelectService)
        singleOf(::InsertService)
        singleOf(::UpdateService)
        singleOf(::DeleteService)
        //DataAccessor
        singleOf(::SelectDataAccessor)
        singleOf(::InsertDataAccessor)
        singleOf(::UpdateDataAccessor)
        singleOf(::DeleteDataAccessor)
    }
}