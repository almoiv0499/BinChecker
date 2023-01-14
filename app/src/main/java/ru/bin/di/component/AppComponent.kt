package ru.bin.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.bin.di.annotation.ApplicationScope
import ru.bin.di.module.DataModule
import ru.bin.di.module.DomainModule
import ru.bin.di.module.PresentationModule
import ru.bin.presentation.fragment.BankInformationFragment
import ru.bin.presentation.fragment.PassBinFragment
import ru.bin.presentation.fragment.StoredBinNumbersFragment

@ApplicationScope
@Component(modules = [DataModule::class, DomainModule::class, PresentationModule::class])
interface AppComponent {

    fun inject(fragment: BankInformationFragment)

    fun inject(fragment: StoredBinNumbersFragment)

    fun inject(fragment: PassBinFragment)

    @Component.Factory
    interface AppComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}