@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    try {
        val parts = str.split(" ")
        if (parts.size != 3)
            return ""
        val year = parts[2].toInt()
        val month: Int
        month = when (parts[1]) {
            "января" -> 1
            "февраля" -> 2
            "марта" -> 3
            "апреля" -> 4
            "мая" -> 5
            "июня" -> 6
            "июля" -> 7
            "августа" -> 8
            "сентября" -> 9
            "октября" -> 10
            "ноября" -> 11
            "декабря" -> 12
            else -> return ""
        }
        val day: Int
        day = when (month) {
            1, 3, 5, 7, 8, 10, 12 -> if (parts[0].toInt() in 1..31) parts[0].toInt() else return ""
            4, 6, 9, 11 -> if (parts[0].toInt() in 1..30) parts[0].toInt() else return ""
            2 -> if (parts[0].toInt() in 1..if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) 29 else 28) parts[0].toInt() else return ""
            else -> return ""
        }
        return String.format("%02d.%02d.%d", day, month, year)
    } catch (e: java.lang.NumberFormatException) {
        return ""
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    try {
        val parts = digital.split(".")
        if (parts.size != 3)
            return ""
        val year = parts[2].toInt()
        val month: String
        month = when (parts[1]) {
            "01" -> "января"
            "02" -> "февраля"
            "03" -> "марта"
            "04" -> "апреля"
            "05" -> "мая"
            "06" -> "июня"
            "07" -> "июля"
            "08" -> "августа"
            "09" -> "сентября"
            "10" -> "октября"
            "11" -> "ноября"
            "12" -> "декабря"
            else -> return ""
        }
        val day: Int
        if (parts[0].length == 2) {
            day = when (parts[1].toInt()) {
                1, 3, 5, 7, 8, 10, 12 -> if (parts[0].toInt() in 1..31) parts[0].toInt() else return ""
                4, 6, 9, 11 -> if (parts[0].toInt() in 1..30) parts[0].toInt() else return ""
                2 -> if (parts[0].toInt() in 1..if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) 29 else 28) parts[0].toInt() else return ""
                else -> return ""
            }
        } else return ""
        return ("$day $month $year")
    } catch (e: java.lang.NumberFormatException) {
        return ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    var result = ""
    val p = phone.filter { it != ' ' && it != '-' }
    for (i in p.indices) {
        if (i == 0) {
            if (p[i] == '+' || p[i].isDigit())
                result += p[i]
            else return ""
        } else {
            if (p[i].isDigit())
                result += p[i]
            else if (p[i] == '(' && p[i + 1].isDigit() && p.filter { it == '(' }.length == 1 && p.filter { it == ')' }.length == 1)
                continue
            else if (p[i] == ')' && p[i - 1].isDigit() && p.filter { it == '(' }.length == 1 && p.filter { it == ')' }.length == 1 && p[i + 1].isDigit())
                continue
            else
                return ""
        }
    }
    return result
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    return try {
        var result = -1
        val parts = jumps.split(" ").filter { it != "-" && it != "%" }
        for (jump in parts)
            if (jump.toInt() > result)
                result = jump.toInt()
        result
    } catch (e: java.lang.NumberFormatException) {
        -1
    }
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    try {
        val jumpsDivided = jumps.split(" ")
        var bestHeight = -1
        for (i in jumpsDivided.indices) {
            if (i % 2 == 0) {
                if (jumpsDivided[i].toInt() > bestHeight && jumpsDivided[i + 1].contains("+"))
                    bestHeight = jumpsDivided[i].toInt()
            } else {
                for (s in jumpsDivided[i]) {
                    if (s !in listOf('+', '%', '-'))
                        return -1
                }
            }
        }
        return bestHeight
    } catch (e: java.lang.NumberFormatException) {
        return -1
    }
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    val parts = expression.split(" ")
    var result = 0
    if (parts.size > 1 || (parts.size == 1 && parts[0].toCharArray().all { it.isDigit() } && parts[0] != "")) {
        for (i in parts.indices) {
            if (i == 0) {
                if (parts[i].toCharArray().all { it.isDigit() })
                    result += parts[i].toInt()
                else {
                    throw IllegalArgumentException()
                }
            } else {
                if (parts[i].toCharArray().all { it.isDigit() }) {
                    when {
                        parts[i - 1] == "+" -> result += parts[i].toInt()
                        parts[i - 1] == "-" -> result -= parts[i].toInt()
                        else -> throw IllegalArgumentException()
                    }
                } else if (parts[i] == "-" || parts[i] == "+") {
                    if (parts[i + 1].toCharArray().all { it.isDigit() })
                        continue
                    else throw IllegalArgumentException()
                } else {
                    throw IllegalArgumentException()
                }
            }
        }
    } else {
        throw IllegalArgumentException()
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val words = str.toLowerCase().split(" ")
    for (i in 0..words.size - 2) {
        if (words[i] == words[i + 1]) {
            for (j in (words.subList(0, i + 1).joinToString(" ").length - 1) downTo 0) {
                if (words.subList(0, i + 1).joinToString(" ")[j] == ' ')
                    return j + 1
                else if (!words.subList(0, i + 1).joinToString(" ").contains(" "))
                    return 0
            }
        }
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    return try {
        val goods = description.split("; ")
        var (me, price) = Pair("", 0.0)
        for (item in goods) {
            if (item.split(" ").size == 2) {
                if (item.split(" ")[1].toDouble() >= price) {
                    me = item.split(" ")[0]
                    price = item.split(" ")[1].toDouble()
                }
            } else return ""
        }
        me
    } catch (e: java.lang.NumberFormatException) {
        ""
    }
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    var result = 0
    val digits = mapOf('M' to 1000, 'D' to 500, 'C' to 100, 'L' to 50, 'X' to 10, 'V' to 5, 'I' to 1)
    for (i in roman.indices) {
        if (digits.keys.contains(roman[i])) {
            if (i > 0 && digits[roman[i]]!! > digits[roman[i - 1]]!!) {
                result -= digits[roman[i - 1]]!! * 2
                result += digits[roman[i]]!!
            } else result += digits[roman[i]]!!
        } else return -1
    }
    return result
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()
