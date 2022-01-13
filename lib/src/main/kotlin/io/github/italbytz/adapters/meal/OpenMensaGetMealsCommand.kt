package io.github.italbytz.adapters.meal

import io.github.italbytz.ports.meal.Category
import io.github.italbytz.ports.meal.GetMealsCommand
import io.github.italbytz.ports.meal.MealCollection
import io.github.italbytz.ports.meal.MealQuery
import kotlinx.coroutines.*

class OpenMensaGetMealsCommand : GetMealsCommand {

    override fun execute(
        inDTO: MealQuery,
        successHandler: (success: List<MealCollection>) -> Unit,
        errorHandler: (error: Throwable) -> Unit
    ) {
        val dataSource = OpenMensaMealDataSource(inDTO.mensa.toLong(), inDTO.date)

                GlobalScope.launch {
            val result = kotlin.runCatching {
                dataSource.retrieveAll()
            }
            result.onSuccess {
                meals ->
                val groupedMeals = meals.groupBy { it.category }
                var collections = mutableListOf<MealCollection>()
                for ((category, meals) in groupedMeals) {
                    collections.add(OpenMensaMealCollection(category,meals))
                }
                successHandler(collections)
            }
            result.onFailure(errorHandler)
        }
    }
}
