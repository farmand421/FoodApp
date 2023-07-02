package com.farmand.foodapp.utils.di

import androidx.fragment.app.Fragment
import com.farmand.foodapp.ui.detail.DetailContracts
import com.farmand.foodapp.ui.favorite.FavoriteContracts
import com.farmand.foodapp.ui.home.HomeContracts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ContractsModule {

    @Provides
    fun homeView(fragment: Fragment): HomeContracts.View {
        return fragment as HomeContracts.View
    }

    @Provides
    fun detailView(fragment: Fragment): DetailContracts.View {
        return fragment as DetailContracts.View
    }

    @Provides
    fun favoriteView(fragment: Fragment): FavoriteContracts.View {
        return fragment as FavoriteContracts.View
    }
}