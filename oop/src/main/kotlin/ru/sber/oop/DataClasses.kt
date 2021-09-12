package ru.sber.oop

data class User(val name: String, val age: Long) {
    lateinit var city: String

    fun equals(other: User): Boolean = equals(other as Any) &&
                if (other::city.isInitialized && this::city.isInitialized) this.city == other.city else false
}

fun main() {
    val user1 = User("Alex", 13)
    val user2 = user1.copy(name = "Heather")
    user1.city = "Omsk"
    val user3 = user1.copy()
    user3.city = "Tomsk"
}