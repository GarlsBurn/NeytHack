package com.bignerdranch.neythack.extensions

fun <T> Iterable<T>.random(): T = this.shuffled().first()

