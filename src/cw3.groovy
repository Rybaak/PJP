import groovy.json.JsonSlurper
import java.text.DecimalFormat
import java.text.SimpleDateFormat

void Zadanie1()
{
    def lista1=['a', 'b', 'c']
    def lista2=[1, 8, 23 ]
    def list11=lista1.collect { it * 2 }
    def list12=lista2.collect { it * 2 }

    println "A"
    lista1.each {println it}
    lista2.each {println it}

    println "B"
    list11.each {println it}
    list12.each {println it}
}

void Zadanie2()
{
    def pmap = [
            Groovy: [ 'Asia', 'Jan' ],
            Grails: [ 'Asia', 'Jan', 'Stefan', 'Mirek' ],
            Java: [ 'Asia', 'Stefan', 'Mirek' ],
            JEE: [ 'Slawek', 'Stefan', 'Janusz' ]
    ]

    println "A)"
    pmap.each { k, v -> println k + ": " + v.size }

    println "B)"
    pmap.findAll({ k, v -> v.size > 2 })
            .each { println it }

    println "C)"
    def programmerLanguages = [:].withDefault { [] }
    pmap.each { k, v ->
        v.each {
            programmerLanguages[it] += k
        }
    }
    programmerLanguages.each { k, v -> println k + ": " + v }
}

void Zadanie3()
{
    def datesString = "abc 1234 2003-34-23 23 67-78 452003-02-28"
    def matchedDates = datesString =~ /(\d+){4}-(\d+){2}-(\d+){2}/

    def dateParser = new SimpleDateFormat("yyyy-MM-dd")
    dateParser.lenient = false // throws exception if date is invalid

    matchedDates.each {
        try {
            println dateParser.parse(it[0])
        } catch(e) {}
    }
}

BigDecimal getCurrentPlnToEuroRate()
{
    def euroToPln = (new JsonSlurper()).parse(new URL('http://api.nbp.pl/api/exchangerates/rates/A/EUR?format=json'))["rates"][0]["mid"]
    return 1 / euroToPln
}

void Zadanie4()
{
    def matchRealNumbersRegex = /((\+|-)?([0-9]+)([.][0-9]+)?)/
    def matchPricesRegex = /((\+|-)?([0-9]+)([.][0-9]+)?[ ][P][L][N])/
    // a)
    println "A)"
    def realNumbersString = "cośtam 3 4.5 wersja-0.3 podwersja 4 minus -8.5kg"
    def matchedRealNumbers = realNumbersString =~ matchRealNumbersRegex

    matchedRealNumbers.each { println it[0] }

    // b)
    println "B)"
    def pricesString = "jajka 5 PLN; mleko 3.5 PLN; chleb 2.13 PLN, a banany po 10 PLNów"

    def plnToEurRate = getCurrentPlnToEuroRate()
    def decimalFormatter = new DecimalFormat("###,##0.00");

    def euroPricesString = pricesString.replaceAll(matchPricesRegex, {
        def price = (it[0] =~ matchRealNumbersRegex)[0][0] as BigDecimal
        return decimalFormatter.format(price * plnToEurRate) + " EUR"
    })

    println pricesString
    println euroPricesString
}

Zadanie1()
Zadanie2()
Zadanie3()
Zadanie4()





