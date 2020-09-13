@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val gradesReversed: MutableMap<Int, MutableList<String>> = mutableMapOf()
    for ((k, v) in grades) {
        if (v in gradesReversed.keys) gradesReversed[v]?.add(k)
        else gradesReversed[v] = mutableListOf(k)
    }
    return gradesReversed
}

/**
 * Простая
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for ((k, v) in a) {
        if (k !in b.keys || v !in b.values) return false
    }
    return true
}

/**
 * Простая
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>): Unit {
    for ((k, v) in b)
        if (k in a.keys && a[k] == v) a.remove(k)
}

/**
 * Простая
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> {
    val result: MutableSet<String> = mutableSetOf()
    for (name in a) {
        if (name in b) result.add(name)
    }
    return result.toList()
}

/**
 * Средняя
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val result: MutableMap<String, String> = mapA.toMutableMap()
    for ((k, v) in mapB) {
        if (result.contains(k)) {
            if (result[k] != null) {
                if (v !in result[k]!!.split(", ")) result[k] += ", $v"
            }
        } else result[k] = v
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val stocks: MutableMap<String, Double> = mutableMapOf()
    val counters: MutableMap<String, Int> = mutableMapOf()
    for ((k, v) in stockPrices) {
        if (k !in stocks.keys) {
            stocks[k] = v
            counters[k] = 1
        } else {
            stocks[k] = stocks[k]!!.plus(v)
            counters[k] = counters[k]!!.plus(1)
        }
    }
    for (k in stocks.keys) {
        stocks[k] = stocks[k]!!.div(counters[k]!!)
    }
    return stocks
}

/**
 * Средняя
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var tempStuff: Pair<String?, Double?> = Pair(null, null)
    for ((k, v) in stuff.values.filter { (key, _) -> key == kind }) {
        if ((tempStuff.second != null && v < tempStuff.second!!) || tempStuff.second == null) {
            tempStuff = Pair(k, v)
        }
    }
    for ((k, _) in stuff.filter { (_, value) -> value == tempStuff })
        return k
    return null
}

/**
 * Средняя
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean =
    word.toLowerCase().all { it -> it in chars.map { it.toLowerCase() } }

/**
 * Средняя
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val counter: MutableMap<String, Int> = mutableMapOf()
    for (element in list) {
        if (element !in counter.keys) {
            counter[element] = 1
        } else counter[element] = counter[element]!!.plus(1)
    }
    return counter.filter { (_, value) -> value > 1 }
}

/**
 * Средняя
 *
 * Для заданного списка слов определить, содержит ли он анаграммы
 * (два слова являются анаграммами, если одно можно составить из второго)
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    var word: String
    for (i in words.indices) {
        word = words[i]
        for (reversedWord in words.drop(i + 1))
            if (word.toCharArray().sorted() == reversedWord.toCharArray().sorted()) return true
    }
    return false
}

/**
 * Сложная
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta")
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat")
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val result: MutableMap<String, MutableSet<String>> = mutableMapOf()
    val showAll: MutableSet<String> = mutableSetOf()
    for ((person, acquaintances) in friends) {
        showAll.add(person)
        showAll += acquaintances
    }
    for (person in showAll) {
        result[person] = friends[person]?.toMutableSet() ?: mutableSetOf()
    }
    return result
}

/**
 * Сложная
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    for (n in list.indices)
        for (m in list.indices)
            if (m != n && list[n] + list[m] == number) return Pair(n, m)
    return Pair(-1, -1)
}

/**
 * Очень сложная
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */
fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val treasuresFiltered = treasures.filter { it.value.first <= capacity }
    val v = getMemTable(treasuresFiltered, capacity).first
    val weight = getMemTable(treasuresFiltered, capacity).second
    val value = getMemTable(treasuresFiltered, capacity).third
    val n = value.size
    var res = v[n][capacity]
    var capacityNow = capacity
    val itemsList: MutableList<Pair<Int, Int>> = mutableListOf()
    for (i in n downTo 1) {
        if (res <= 0)
            break
        if (res == v[i - 1][capacityNow])
            continue
        else {
            itemsList.add(Pair(weight[i - 1], value[i - 1]))
            res -= value[i - 1]
            capacityNow -= weight[i - 1]
        }
    }
    val selected: MutableSet<String> = mutableSetOf()
    for (s in itemsList) {
        for ((k, v) in treasures) {
            if (v == s)
                selected.add(k)
        }
    }
    return selected
}

fun getWeightAndValue(treasures: Map<String, Pair<Int, Int>>): Pair<List<Int>, List<Int>> {
    val weight: MutableList<Int> = mutableListOf()
    val value: MutableList<Int> = mutableListOf()
    for ((i, j) in treasures.values) {
        weight.add(i)
        value.add(j)
    }
    return Pair(weight, value)
}

fun getMemTable(
    treasures: Map<String, Pair<Int, Int>>,
    capacity: Int
): Triple<MutableList<MutableList<Int>>, List<Int>, List<Int>> {
    val weight: List<Int> = getWeightAndValue(treasures).first
    val value: List<Int> = getWeightAndValue(treasures).second
    val n = value.size
    val v: MutableList<MutableList<Int>> = mutableListOf(mutableListOf())
    v.clear()
    for (i in 0..n) {
        v.add(mutableListOf())
        for (a in 0..capacity)
            v[i].add(0)
    }
    for (i in 0..n) {
        for (a in 0..capacity) {
            if (i == 0 || a == 0)
                v[i][a] = 0
            else if (weight[i - 1] <= a)
                v[i][a] = maxOf(value[i - 1] + v[i - 1][a - weight[i - 1]], v[i - 1][a])
            else
                v[i][a] = v[i - 1][a]
        }
    }
    return Triple(v, weight, value)
}