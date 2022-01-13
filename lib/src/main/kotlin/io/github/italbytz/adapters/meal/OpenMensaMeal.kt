package io.github.italbytz.adapters.meal

import io.github.italbytz.ports.meal.*

class OpenMensaMeal(
    override val name: String,
    override val image: String,
    override val price: Price,
    override val allergens: java.util.EnumSet<Allergens>,
    override val additives: java.util.EnumSet<Additives>,
    override val category: Category
) : Meal {
}