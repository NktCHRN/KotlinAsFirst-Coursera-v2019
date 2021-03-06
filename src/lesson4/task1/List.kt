@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt
import kotlin.math.pow

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) =
    list.map { if (it > 0) -it else it }

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double =
    if (v.isEmpty()) 0.0
    else sqrt(v.map { it * it }.sum())

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
    if (list.isEmpty()) 0.0
    else list.sum() / list.size

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val m = mean(list)
    for (i in 0 until list.size) {
        list[i] -= m
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var c = 0
    for (i in a.indices) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var px = 0
    for (i in p.indices) {
        px += p[i] * x.toDouble().pow(i).toInt()
    }
    return px
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    val oldList = list.toList()
    var sumNow = 0
    for (i in 0 until list.size) {
        list[i] += sumNow
        sumNow += oldList[i]
    }
    return list
}

fun isPrime(n: Int): Boolean {
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val primes: MutableList<Int> = mutableListOf()
    var nRem = n
    while (nRem % 2 == 0) {
        primes.add(2)
        nRem /= 2
    }
    for (i in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (nRem == 1) break
        if (isPrime(i))
            while (nRem % i == 0) {
                primes.add(i)
                nRem /= i
            }
    }
    if (nRem > 1) primes.add(nRem)
    return primes
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result: MutableList<Int> = mutableListOf()
    var nNow = n
    while (nNow > 0) {
        result.add(nNow % base)
        nNow /= base
    }
    if (n == 0) {
        result.add(0)
    }
    return result.asReversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val result: List<Int> = convert(n, base).toList()
    var resultString = ""
    for (item in result) {
        if (item > 9) {
            resultString += (item - 10 + 97).toChar()
        } else resultString += item
    }
    return resultString
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    val revDig = digits.asReversed()
    for (i in digits.indices) {
        result += base.toDouble().pow(i).toInt() * revDig[i]
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val result: MutableList<Int> = mutableListOf()
    for (symbol in str) {
        if (symbol.toInt() in 97..122)
            result.add(symbol.toInt() + 10 - 97)
        else
            result.add(symbol.toInt() - 48)
    }
    return decimal(result, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var result = ""
    for (i in 1..n / 1000)
        result += "M"
    result += decision(n / 100 % 10, 'C', 'D', 'M')
    result += decision(n / 10 % 10, 'X', 'L', 'C')
    result += decision(n % 10, 'I', 'V', 'X')
    return result
}

fun decision(n: Int, s1: Char, s2: Char, s3: Char): String {    //IVX
    var dec = ""
    if (n > 0) {
        when (n) {
            9 -> dec += s1.toString() + s3.toString()
            in 5..8 -> {
                dec += s2
                for (i in 1..n - 5)
                    dec += s1
            }
            4 -> dec += s1.toString() + s2.toString()
            else -> for (i in 1..n)
                dec += s1
        }
    }
    return dec
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var result = ""
    result += threeRank(n / 1000)
    if (n / 10000 % 10 != 1) {
        when (n / 1000 % 10) {
            1 -> result = result.substring(0, result.length - 3) + "на "
            2 -> result = result.substring(0, result.length - 2) + "е "
        }
    }
    if (n / 10000 % 10 == 1) result += "тысяч "
    else when (n / 1000 % 10) {
        1 -> result += "тысяча "
        in 2..4 -> result += "тысячи "
        in 5..9 -> result += "тысяч "
        0 -> if (n / 10000 > 0) result += "тысяч "
    }
    result += threeRank(n % 1000)
    if (result.drop(result.length - 1) == " ") result = result.substring(0, result.length - 1)
    return result
}

fun threeRank(n: Int): String {
    var result = ""
    val numbers: List<String> = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    when (n / 100) {
        in 5..9 -> result += numbers[n / 100 - 1] + "сот "
        in 3..4 -> result += numbers[n / 100 - 1] + "ста "
        2 -> result += "двести "
        1 -> result += "сто "
    }
    when (n / 10 % 10) {
        9 -> result += "девяносто "
        in 5..8 -> result += numbers[n / 10 % 10 - 1] + "десят "
        4 -> result += "сорок "
        in 2..3 -> result += numbers[n / 10 % 10 - 1] + "дцать "
        1 -> {
            result += when (n % 10) {
                0 -> "десять "
                1, 3 -> numbers[n % 10 - 1] + "надцать "
                2 -> "двенадцать "
                4 -> "четырнадцать "
                else -> numbers[n % 10 - 1].replace("ь", "") + "надцать "
            }
            return result
        }
    }
    if (n % 10 - 1 > 0)
        result += numbers[n % 10 - 1] + " "
    else if (n % 10 == 1)
        result += "один "
    return result
}