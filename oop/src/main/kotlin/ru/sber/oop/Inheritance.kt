package ru.sber.oop

open class Room(val name: String, val size: Int) {

    constructor(name: String) : this(name, 100)

    private var monster: Monster = Goblin("physical", 30)
    protected open val dangerLevel = 5

    fun description() = "Room: $name"

    private fun Monster.getSalutation(): String = "Ave, \$username, morituri te salutant!"

    open fun load() = monster.getSalutation()

}

open class TownSquare : Room("Town Square", 1000) {
    override val dangerLevel = super.dangerLevel - 3

    final override fun load() = "You are standing right in the center of a town square."
}

