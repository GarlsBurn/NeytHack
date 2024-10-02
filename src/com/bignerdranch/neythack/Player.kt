package com.bignerdranch.neythack
import com.bignerdranch.neythack.extensions.random as randomizer
import java.io.File
import kotlin.random.Random

class Player(_name: String,
            override var healthPoints: Int,
            val isBlessed: Boolean,
            private val isImmortal: Boolean
    ): Fightable          {

    var name = _name
        get() = "${field.capitalize()} of $hometown"
        private set(value) {
            field = value.trim()
        }

    val hometown by lazy {selectHometown() }
    var currentPosition = Coordinate(0,0)


    private fun selectHometown() = File("data/towns.txt").readText().split("\n")
        .randomizer()

    init {
        require(healthPoints > 0, { "HeaalthPoints must be greater than zero" })
        require(name.isNotBlank(), { "Player must has a name" })
    }

    constructor(name: String) : this(
        name,
        healthPoints = 100,
        isBlessed = true,
        isImmortal = false
    )

    fun castFireball(numFireball: Int = 2) {
        println("A glass of Fireball springs into existence. (x$numFireball)")

    }


    fun formatHealthStatus() = when (healthPoints) {
            100 -> " is in excelent condition!"
            in 90..99 -> " has a few scratches."
            in 75..89 -> if (isBlessed) {
                "has some minor wounds but is healing quite quickly!"
            } else {
                " has some minor wounds."
            }

            in 15..74 -> " looks pretty hurt."
            else -> " is in awful condition!"
    }

    fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 49 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }

    override val diceCount: Int = 3
    override val diceSides: Int = 6
    override val damageRoll: Int
        get() = (0 until diceCount).map {
                Random.nextInt(diceSides + 1)
            }.sum()


    override fun attack(opponent: Fightable): Int {
        val damageDefault = if (isBlessed){
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDefault
        return damageDefault
    }
}
