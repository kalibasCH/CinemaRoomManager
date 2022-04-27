package cinema

import java.math.BigDecimal
import java.math.RoundingMode

const val TEN = 10
const val ELN = 8
const val TWO = 2
const val ONH = 100

var rows = 0
var seats = 0
var countPurchased = 0
var countIncome = 0
var listWithBuying = MutableList(rows) {
    MutableList(seats) { "S" }
}

fun main() {
    println("Enter the number of rows:")
    rows = readLine()!!.toInt()
    println("Enter the number of seats in each row:")
    seats = readLine()!!.toInt()
    listWithBuying = MutableList(rows) {
        MutableList(seats) { "S" }
    }
    one()
}

fun one() {
    println("1. Show the seats\n2. Buy a ticket\n3. Statistics \n0. Exit")
    when (readLine()!!.toInt()) {
        1 -> show()
        2 -> buy()
        3 -> statistics()
        0 -> return
    }
}

fun show() {
    val listOne = mutableListOf<String>()
    listOne.add(" ")
    for (i in 1..seats) {
        listOne.add(i.toString())
    }
    println("Cinema:")
    println(listOne.joinToString(" "))
    for (i in 1..rows) {
        print("$i ")
        println(listWithBuying[i - 1].joinToString(" "))
    }
    println("")
    one()
}

fun buy() {
    try {
        println("Enter a row number:")
        val nRow = readLine()!!.toInt() // 4
        println("Enter a seat number in that row:")
        val nSeat = readLine()!!.toInt() // 2
    if (listWithBuying[nRow - 1][nSeat - 1] == "B") {
        println("That ticket has already been purchased!")
        buy()
    } else {
        listWithBuying[nRow - 1][nSeat - 1] = "B"
    }
    if (rows * seats <= 60) {
        print("Ticket price:")
        println("$$TEN\n")
        countPurchased++
        countIncome += TEN
        one()
    } else {
        val a = rows / TWO // 4
        if (nRow <= a) {
            print("Ticket price:")
            println("$$TEN\n")
            countPurchased++
            countIncome += TEN
            one()
        } else {
            print("Ticket price:")
            println("$$ELN\n")
            countPurchased++
            countIncome += ELN
            one()
        }
    }
    } catch (e: IndexOutOfBoundsException) {
        println("Wrong input!")
        buy()
    }
}

fun totalIncome(): Int {
    return if (rows * seats <= 60) {
        rows * seats * TEN
    } else {
        val a = rows / TWO
        (a * seats * 10) + ((rows - a) * seats * 8)
    }
}

fun percentage(): BigDecimal {
    return if (countPurchased == 0) {
        "0.00".toBigDecimal()
    } else {
        val a = countPurchased / (rows.toDouble() * seats.toDouble()) * ONH.toDouble()
        val b = a.toBigDecimal()
        return b.setScale(2, RoundingMode.HALF_DOWN)
    }
}

fun statistics() {
    println("Number of purchased tickets: $countPurchased\nPercentage: ${percentage()}%" +
            "\nCurrent income: $$countIncome\nTotal income: $${totalIncome()}\n")
    one()
}