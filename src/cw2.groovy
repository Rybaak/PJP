import java.util.List
import javax.swing.JOptionPane
import groovy.lang.Closure

void Zadanie1()
{
    def maxVal, indList;
    (maxVal, indList) = maximum([5, 3, 5, 5, 1, 5])
    println "Maximum: $maxVal"
    println "Indexes: $indList"

    (maxVal, indList) = maximum(['a', 'c', 'c', 'c', 'b'])
    println "Maximum: $maxVal"
    println "Indexes: $indList"
}

String[] maximum(array)
{
    def arrayMax = array.max()
    def indexRes = array.findIndexValues { it == arrayMax }
    return [arrayMax, indexRes]
}

void Zadanie2()
{
    def ints = getInts()

    println ints
}

Integer[] getInts()
{
    def results = []
    def value;

    while (true) {
        value = JOptionPane.&showInputDialog("Podaj liczbę", value)

        if (value == null)
            break

        if (!value.isInteger()) {
            value = "Zła wartość: $value"
            continue;
        }

        results << value.toInteger()
        value = ''
    }

    return results
}

void Zadanie3()
{
    def numbers = [1,2,3,4]
    def incrementedNumbers = apply(numbers, {it+1})

    println numbers
    println incrementedNumbers

    def words = ["aa", "bb", "cc", "dd"]
    def multipledWords = apply(words, {it*3})

    println words
    println multipledWords
}

List apply(List l, Closure c)
{
    List values = [];
    l.each { values.add(c(it)) }
    return values;
}

void Zadanie4()
{
    println getData(Integer) { it > 0 }
    println getData() { it.size() > 3 }
    println getData()
    println getData(BigDecimal)
}

def getData(p1 = null, p2 = null)
{
    def results = []
    def value = '';

    while (true) {
        value = JOptionPane.&showInputDialog("Podaj wartość", value)

        if (value == null)
            break

        if (p1 instanceof Class)
            try {
                value = value.asType(p1)
                if (p2 instanceof Closure && !p2(value)) {
                    value = "Zła wartość: $value"
                    continue
                }
            } catch(e) {
                value = "Zła wartość: $value"
                continue
            }
        else if (p1 instanceof Closure && !p1(value)) {
            value = "Zła wartość: $value"
            continue;
        }

        results << value
        value = ''
    }

    return results
}

//Zadanie1()
//Zadanie2()
//Zadanie3()
//Zadanie4()





