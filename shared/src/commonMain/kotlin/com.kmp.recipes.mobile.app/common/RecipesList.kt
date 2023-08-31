package com.kmp.recipes.mobile.app.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.navigator.Navigator
import com.kmp.recipes.mobile.app.Dimens
import com.kmp.recipes.mobile.app.common.data.Recipe
import com.kmp.recipes.mobile.app.recipe_details.RecipeDetailsScreen
import com.kmp.recipes.mobile.app.sharedres.SharedRes
import com.seiko.imageloader.rememberAsyncImagePainter
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource


@Composable
fun RecipesListing(recipesList: List<Recipe>, navigator: Navigator) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(
                start = Dimens.defaultSpacing,
                end = Dimens.defaultSpacing
            )
        ) {
            repeat(recipesList.size) {
                Column(modifier = Modifier.clickable {
                    navigator.push(
                        RecipeDetailsScreen(
                            recipesList[it]
                        )
                    )
                }) {
                    Card(
                        Modifier.fillMaxWidth()
                            .height(Dimens.popularRecipeImageHeight)
                            .padding(top = Dimens.smallSpacing)
                            .clipToBounds(),
                    ) {
                        val painter = rememberAsyncImagePainter(recipesList[it].image)
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = Dimens.smallSpacing),
                        text = recipesList[it].label,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        maxLines = 1
                    )

                    Text(
                        text = recipesList[it].description,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 3
                    )

                    Row(
                        modifier = Modifier.padding(
                            top = Dimens.smallSpacing,
                            bottom = Dimens.smallSpacing
                        ),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        PopularRecipeCookingDuration(recipesList[it])
                        Spacer(Modifier.width(Dimens.defaultSpacing))
                        PopularRecipeDifficultyLevel(recipesList[it])
                    }
                }
            }
        }
    }
}

@Composable
fun PopularRecipeCookingDuration(recipe: Recipe) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(Dimens.iconSizeMedium),
            painter = painterResource(SharedRes.images.ic_time),
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            contentDescription = stringResource(SharedRes.strings.recipe_cooking_duration_image)
        )
        Spacer(Modifier.width(Dimens.smallSpacing))
        Text(
            text = recipe.duration,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun PopularRecipeDifficultyLevel(recipe: Recipe) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(Dimens.iconSizeMedium),
            painter = painterResource(SharedRes.images.ic_recipe),
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            contentDescription = stringResource(SharedRes.strings.recipe_difficulty_level_image)
        )
        Spacer(Modifier.width(Dimens.smallSpacing))
        Text(
            text = recipe.level,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}