package com.kmp.recipes.mobile.app.ui.listing

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.kmp.recipes.mobile.app.data.datasource.model.ApiResultState
import com.kmp.recipes.mobile.app.data.datasource.model.Recipe
import com.kmp.recipes.mobile.app.data.repository.RecipesRepository
import kotlinx.coroutines.launch

class RecipesScreenModel(private val repository: RecipesRepository) :
    StateScreenModel<RecipesListingScreenState>(RecipesListingScreenState.Init) {

    fun fetchRecipesList(id: String) {
        coroutineScope.launch {
            when (id) {
                "view all" -> {
                    fetchAllRecipes()
                }

                "favourites" -> {
                    fetchFavouriteRecipes()
                }

                else -> {
                    fetchRecipesByCategory(id)
                }
            }
        }
    }

    private suspend fun fetchFavouriteRecipes() {
        repository.fetchFavouriteRecipes().collect {
            parseRecipesResult(it)
        }
    }

    private suspend fun fetchAllRecipes() {
        repository.fetchAllRecipes().collect {
            parseRecipesResult(it)
        }
    }

    private suspend fun fetchRecipesByCategory(id: String) {
        repository.fetchRecipesListByCategory(id).collect {
            parseRecipesResult(it)
        }
    }

    private fun parseRecipesResult(it: ApiResultState) {
        when (it) {
            is ApiResultState.OnFailure -> {
                val failureMessage = it.errorMessage
                mutableState.value = RecipesListingScreenState.Error(failureMessage)
            }

            is ApiResultState.OnSuccess<*> -> {
                val list = it.data as List<Recipe>
                mutableState.value = RecipesListingScreenState.Content(list)
            }
        }
    }
}