package io.github.italbytz.adapters.meal

import io.github.italbytz.ports.meal.Price

class OpenMensaPrice(
    override val employees: Double?,
    override val others: Double?,
    override val pupils: Double?,
    override val students: Double?
) : Price {
}