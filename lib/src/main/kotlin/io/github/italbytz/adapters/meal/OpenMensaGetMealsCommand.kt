package io.github.italbytz.adapters.meal

import io.github.italbytz.ports.meal.Category
import io.github.italbytz.ports.meal.GetMealsCommand
import io.github.italbytz.ports.meal.MealCollection
import io.github.italbytz.ports.meal.MealQuery

class OpenMensaGetMealsCommand : GetMealsCommand {

    override suspend fun execute(inDTO: MealQuery) : List<MealCollection> {
        val dataSource = OpenMensaMealDataSource(inDTO.mensa.toLong(), inDTO.date)
        val meals = dataSource.retrieveAll()
        val groupedMeals = meals.groupBy { it.category }
        var collections = mutableListOf<MealCollection>()
        for ((category, meals) in groupedMeals) {
            collections.add(OpenMensaMealCollection(category,meals))
        }
        return collections
    }
}
