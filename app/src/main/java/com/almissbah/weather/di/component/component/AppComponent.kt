package com.almissbah.weather.di.component.component

import android.app.Application
import com.almissbah.weather.WeatherForcastApp
import com.almissbah.weather.di.component.module.ActivityModule
import com.almissbah.weather.di.component.module.ApiModule
import com.almugdad.takhlesy.di.module.FragmentModule
import com.almugdad.takhlesy.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepoModule::class, AndroidSupportInjectionModule::class, ViewModelModule::class, FragmentModule::class, ActivityModule::class])
interface AppComponent : AndroidInjector<WeatherForcastApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(WeatherForcastApp: WeatherForcastApp)
}