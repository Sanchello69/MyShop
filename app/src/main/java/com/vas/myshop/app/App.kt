package com.vas.myshop.app

import android.app.Application
import com.vas.feature_cart_screen.di.CartDepsStore
import com.vas.feature_main_screen.di.MainDepsStore
import com.vas.feature_product_details_screen.di.DetailsDepsStore
import com.vas.myshop.di.AppComponent
import com.vas.myshop.di.DaggerAppComponent

class App : Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()

        MainDepsStore.deps = appComponent
        DetailsDepsStore.deps = appComponent
        CartDepsStore.deps = appComponent
    }
}