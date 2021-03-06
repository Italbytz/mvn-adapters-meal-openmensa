/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package io.github.italbytz.adapters.meal

import io.github.italbytz.infrastructure.openmensa.Meal
import io.github.italbytz.infrastructure.openmensa.MensaClosedException
import io.github.italbytz.infrastructure.openmensa.NoMealsForDateException
import io.github.italbytz.infrastructure.openmensa.OpenMensaService
import io.github.italbytz.ports.meal.MealQuery
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LibraryTest {
    @Test fun getTodaysMeals() {
        val classUnderTest = OpenMensaGetMealsCommand()
        try {
            runBlocking {
                val result = classUnderTest.execute(MockMealQuery(1, LocalDate.now()))
                assertFalse(result.isEmpty())
            }
        } catch (e: NoMealsForDateException) {

        } catch (e: MensaClosedException) {

        }
    }
}

class MockMealQuery(override val mensa: Int, override val date: LocalDate) : MealQuery {
}
