@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.sqrt
import kotlin.math.pow
import kotlin.math.PI

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var counter = 0
    var x = n
    do {
        counter ++
        x /= 10
    } while (x > 0)
    return counter
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var result = 1
    var r1 = 0
    for(i in 1..n-1) {
        result += r1
        r1 = result - r1
    }
    return result
    //
    //if (n <= 2) return 1
    //else return fib(n - 1) + fib(n - 2)
}

/**
 * Простая
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var x: Int
    val y: Int
    if (m > n) {
        x = m
        y = n
    }
    else {
        x = n
        y = m
    }
    if (x % m == 0 && x % n == 0)
        return x
    else {
        for (i in 2..y){
            if ((x*i) % y == 0) return x*i
        }
    }
    return -1
}

/**
 * Простая
 * ! Дод.
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    if (n % 2 == 0) return 2
    if (isPrime(n)) return n
    else {
        for (i in 3..n step 2)
            if (n % i == 0)
                return i
        return 1
    }
}

/**
 * Простая
 * 
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    if (n % 2 == 0) return n / 2
    else if (isPrime(n)) return 1
    else {
        for (i in 3..(n / 2) step 2)
            if (n % i == 0)
                return n / i
        return 1
    }
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if (m==1 || n==1) return true
    if (m%2==0 && n%2 == 0) return false
    if (m==n) return false
    val x: Int
    val y: Int
    if (m > n) {
        x = m
        y = n
    }
    else {
        x = n
        y = m
    }
    if (isPrime(y)){
        if (x % y == 0) return false
        else return true
    }
    for (i in 3..if (y <= x/2) y else {x/2} step 2) {
        if (m % i == 0 && n % i == 0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in m..n){
        if ((sqrt(i.toDouble()).toInt() * sqrt(i.toDouble()).toInt()) == i) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int =
    when {
        x == 1 -> 0
        else -> {
            1 + collatzSteps(if (x % 2 == 0) x/2 else 3*x+1)
        }
    }

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var s = 0.0
    var c = 1
    var m: Double
    var xr = 0.0
    if (x > PI * 2){
        for (i in 2..Int.MAX_VALUE step 2){
            if (x - PI * i <= PI * 2){
                xr = x - PI * i
                break
            }
        }
    }
    else xr = x
    do {
        m = xr.pow(c) / factorial(c)
        if ((c + 1) % 4 == 0) s -= m
        else s += m
        c += 2
    } while (m >= eps)
    return s
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var s = 1.0
    var c = 2
    var m: Double
    var xr = 0.0
    if (x > PI * 2){
        for (i in 2..Int.MAX_VALUE step 2){
            if (x - PI * i <= PI * 2){
                xr = x - PI * i
                break
            }
        }
    }
    else xr = x
    do {
        m = xr.pow(c) / factorial(c)
        if (c > 3 && c % 4 == 0) s += m
        else s -= m
        c += 2
    } while (m >= eps)
    return s
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    val digits = digitNumber(n)
    var result = 0
    var oldNumberNow = n
    var number: Int
    for (i in 0..digits-1){
        number = oldNumberNow / 10.0.pow(digits - i - 1).toInt()
        oldNumberNow -= number * 10.0.pow(digits - i - 1).toInt()
        result += number * 10.0.pow(i).toInt()
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var digits = digitNumber(n)
    var oldNumberNow = n
    for (i in 0..digits-1) {
        if (digits > 2){
            if (oldNumberNow / 10.0.pow(digits - 1).toInt() == oldNumberNow % 10) {
                oldNumberNow -= oldNumberNow / 10.0.pow(digits - 1).toInt() * 10.0.pow(digits - 1).toInt()
                oldNumberNow /= 10
                digits = digitNumber(oldNumberNow)
            }
        }
        else if (digits == 1) return true
        else if (digits == 2 && oldNumberNow / 10 == oldNumberNow % 10) return true
    }
    return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean =
    when {
        (digitNumber(n) == 1) -> false
        (n % 10 != (n / 10) % 10) -> true
        else -> hasDifferentDigits(n / 10)
    }

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var c = 1
    var f = 0
    var numbersLeft = n
    while (numbersLeft > 0) {
        f = fib(c)
        numbersLeft -= digitNumber(f)
        c++
    }
    f = (f / (10.0.pow(-numbersLeft).toInt())) % 10
    return  f
}