package com.bignerdranch.neythack

fun main(args: Array<String>){
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if (isJugglingProficient){
        swordsJuggling = 2
    }
    try{
    proficiencyCheck(swordsJuggling)
    swordsJuggling = swordsJuggling!!.plus(1)
    } catch(e: Exception){
        println(e)
    }
    println("You juggle $swordsJuggling swords!")
}

fun proficiencyCheck(swordsJuggling: Int?){
    //swordJuggling ?: throw UnskilledSwordJugglerEception()
    checkNotNull(swordsJuggling, {"com.bignerdranch.neythack.Player cannot juggle swords "})
}

class UnskilledSwordJugglerEception():
        IllegalStateException("com.bignerdranch.neythack.Player cannot juggle swords")