package com.bignerdranch.neythack

open class Room(val name: String){
    protected open val dangerLevel = 5
    var monster: Monster? = Goblin()
    fun descreption() = "Room, $name\n" +
            "Danger Level: $dangerLevel\n" +
            "Creature: ${monster?.description ?: "none."}"

   open fun load() = "Nothing much to see here..."
}

class TownSquare: Room("TownSquare"){
    override val dangerLevel = super.dangerLevel - 3
    private var bellSound = "GWONG"
    final override fun load() = "The villagers rally and cheer as you enter \n " +
            "${ringBell()}"
    private fun ringBell() = "The bell tower announces yor arrival. $bellSound"
}