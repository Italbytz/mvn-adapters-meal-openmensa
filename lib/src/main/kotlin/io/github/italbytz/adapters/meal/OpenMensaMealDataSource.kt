package io.github.italbytz.adapters.meal

import io.github.italbytz.infrastructure.openmensa.MensaClosedException
import io.github.italbytz.infrastructure.openmensa.NoMealsForDateException
import io.github.italbytz.infrastructure.openmensa.OpenMensaService
import io.github.italbytz.ports.meal.Additives
import io.github.italbytz.ports.meal.Allergens
import io.github.italbytz.ports.meal.Category
import io.github.italbytz.ports.meal.Meal

class OpenMensaMealDataSource(val mensa: Long, val date: java.time.LocalDate) : io.github.italbytz.ports.common.DataSource<Int,Meal> {

    val api = OpenMensaService()
    val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override suspend fun retrieve(id: Int): Meal {
        throw NotImplementedError()
    }

    override suspend fun retrieveAll(): List<Meal> {
        val result = api.getMeals(mensa, date.format(formatter)).execute().body()!!
        return result.map { it.toMeal() }
    }
}

fun io.github.italbytz.infrastructure.openmensa.Meal.toMeal() : Meal {
    var category = when(this.category) {
        "Desserts" -> Category.DESSERT
        "Beilagen" -> Category.SIDEDISH
        else -> Category.DISH
    }
    return OpenMensaMeal(this.name,"",
        OpenMensaPrice(this.prices.employees, this.prices.others, this.prices.pupils, this.prices.students),
        java.util.EnumSet.noneOf(Allergens::class.java), java.util.EnumSet.noneOf(Additives::class.java), category)
}