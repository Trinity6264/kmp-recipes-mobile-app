package com.kmp.recipes.mobile.app.common.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RecipesData(
    @SerialName("sections") val sections: Sections,
    @SerialName("recipes") val recipesList: List<Recipe>
)
