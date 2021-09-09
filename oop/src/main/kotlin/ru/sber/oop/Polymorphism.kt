package ru.sber.oop

import kotlin.random.Random

interface Fightable {
    val powerType: String
    var healthPoints: Int
    val damageRoll: Int
        get() = Random.nextInt(1, 21)

    fun attack(opponent: Fightable): Int
}

class Player(val name: String,
             var isBlessed: Boolean,
             override val powerType: String,
             override var healthPoints: Int
             ) : Fightable {

    override fun attack(opponent: Fightable): Int {
        val dmg = if (isBlessed) damageRoll * 2 else damageRoll
        opponent.healthPoints -= dmg
        return dmg
    }
}

abstract class Monster() : Fightable {
    abstract val name: String
    abstract val description: String

    override fun attack(opponent: Fightable): Int {
        val dmg = damageRoll
        opponent.healthPoints -= dmg
        return dmg
    }
}

class Goblin(override val powerType: String, override var healthPoints: Int) : Monster() {
    override val name = "Goblin"
    override val description = "A small (and yet disgusting) humanoid with green skin"

    override val damageRoll: Int
        get() = super.damageRoll / 2
}

