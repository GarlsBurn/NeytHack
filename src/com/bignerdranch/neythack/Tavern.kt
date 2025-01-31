package com.bignerdranch.neythack
import com.bignerdranch.neythack.extensions.random
import java.io.File

const val TAVERN_NAME = "Taernly's Folly"


val patronList = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatron = mutableSetOf<String>()
val menuList = File("data/tavern-menu-items.txt").readText().split("\n")
val patronGold = mutableMapOf<String, Double>()



fun main(args: Array<String>) {

    //howManyPintRemain(5.0, 12)

    (0..9).forEach{
        val first = patronList.random()
        val last = lastName.random()
        val name = "$first $last"
        uniquePatron += name
    }

    uniquePatron.forEach{
        patronGold[it] = 6.0
    }

    var count = 0
    while (count <= 9){
        placeOrder(menuList.random(), uniquePatron.random())
        count++
    }
    displayPatronBalance()

}

private fun String.toDragonSpeak(phrase:String) =
    this.replace(Regex("[aeiou]")) {
        when (it.value) {
            "a" -> "4"
            "e" -> "3"
            "i" -> "1"
            "o" -> "0"
            "u" -> "|_|"
            else -> it.value
        }
    }



private fun placeOrder(menuData: String, patronName: String) {
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speaks with $tavernMaster about their order.")


    val menuItems = menuData.split(",")
    if (menuItems.size >= 3){
        val (type, name, price) = menuItems
        val message = "$patronName buys a $name ($type) for $price"
        println(message)
        performPurchase(price.toDouble(), patronName)
        val phrase = if (name == "Dragon's Breath"){
            "$patronName exclaims".toDragonSpeak("Ah, delicious $name!")}
        else{
            "$patronName says: Thanks for the $name"
        }
        println(phrase)
    } else {
        println("Invalid menu data: $menuData")
    }


}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price
    if (patronGold[patronName]!! <= 0){
        uniquePatron.remove(patronName)
        patronGold.remove(patronName)
    }
}

fun displayPatronBalance(){
    patronGold.forEach{ patron, balance ->
        println("$patron, balance = ${"%.2f".format(balance)}")
    }
}


private fun howManyPintRemain(gal: Double, pint: Int){
    val remainPint = gal/0.125 - pint
    println("Remain of pint is: $remainPint")
}





