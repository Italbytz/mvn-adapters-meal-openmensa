package io.github.italbytz.adapters.meal

import io.github.italbytz.ports.meal.Category
import io.github.italbytz.ports.meal.Meal
import io.github.italbytz.ports.meal.MealCollection

class OpenMensaMealCollection(
    override val category: Category,
    override val meals: List<Meal>
) : MealCollection {
}