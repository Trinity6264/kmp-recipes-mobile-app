package com.kmp.recipes.mobile.app.common.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sections(
//    @SerialName("discover") val discover: DiscoverRecipesData,
//    @SerialName("categories") val categories: CategoriesData,
    @SerialName("popular_recipes") val popularRecipes: PopularRecipesData
)
