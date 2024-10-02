package com.bignerdranch.neythack

enum class Direction(val coordinate: Coordinate ){
    NORTH(Coordinate(0, -1)),
    EAST(Coordinate(1, 0)),
    SOUTH(Coordinate(0, 1)),
    WEST(Coordinate(-1, 0));

    fun updateCoordinate(playerCoordinate: Coordinate) =
        coordinate + playerCoordinate


   /* val matrixMap = Array(2) { Array(3) { '0' } }

    fun map(){
        val map = createMatrix()

        for(row in map){
            for (element in row) {
                print("$element ")
            }
            println() }
    }

    fun createMatrix(): Array<Array<Char>> {

        matrixMap[0][0] = 'x'
        matrixMap[0][1] = '0'
        matrixMap[1][0] = '0'
        matrixMap[1][1] = '0'
        matrixMap[0][2] = '0'
        matrixMap[1][2] = ' '
        return matrixMap
    }*/
}

data class Coordinate (val x: Int, val y: Int){
    val isInBounds = x >= 0 && y >= 0

    operator fun plus(other: Coordinate) = Coordinate(x + other.x, y + other.y)
}