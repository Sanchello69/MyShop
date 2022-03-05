package com.vas.feature_cart_screen.di

import androidx.lifecycle.ViewModel
import com.vas.feature_cart_screen.presentation.CartViewModelFactory
import com.vas.feature_cart_screen.presentation.MyCartFragment
import dagger.Component
import kotlin.properties.Delegates

@Component(dependencies = [CartDeps::class])
internal interface CartComponent {
    fun inject(fragment: MyCartFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: CartDeps): Builder

        fun build(): CartComponent
    }
}

interface CartDeps {
    val cartViewModelFactory: CartViewModelFactory
}

internal class CartComponentViewModel : ViewModel(){

    val newCartComponent = DaggerCartComponent.builder().deps(CartDepsProvider.deps).build()
}

interface CartDepsProvider {
    val deps: CartDeps

    companion object : CartDepsProvider by CartDepsStore
}

object CartDepsStore : CartDepsProvider {
    override var deps: CartDeps by Delegates.notNull()
}