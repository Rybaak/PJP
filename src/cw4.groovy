import groovy.json.JsonSlurper
import java.text.DecimalFormat

void Zadanie1()
{
    def bigProjectsOutput = new File('ProjektyDuze.txt')
    bigProjectsOutput.text = ''
    def programmersOutput = new File('Programisci.txt')
    programmersOutput.text = ''

    def input = new File('C:\\Users\\Maciek\\IdeaProjects\\PJP\\Projekty.txt')

    def programmersLanguages = [:].withDefault { [] }
    input.each {
        if (it.size() == 0)
            return

        def values = it.split("\t")
        def language = values[0]
        def programmers = values.drop(1)

        if (programmers.size() > 3)
            bigProjectsOutput << "$language\n"

        programmers.each {
            programmersLanguages[it] += language
        }
    }

    programmersLanguages.each { programmer, languages ->
        programmersOutput << programmer + "\t" + languages.join("\t") + "\n"
    }
}

BigDecimal getCurrentPlnToEuroRate()
{
    def euroToPln = (new JsonSlurper()).parse(new URL('http://api.nbp.pl/api/exchangerates/rates/A/EUR?format=json'))["rates"][0]["mid"]
    return 1 / euroToPln
}

void Zadanie2()
{
    def plnToEurRate = getCurrentPlnToEuroRate()
    def decimalFormatter = new DecimalFormat("###,##0.00")

    def input = new File('C:\\Users\\Maciek\\IdeaProjects\\PJP\\MenuPl.txt')
    def output = new File('MenuEur.txt')

    output.text = ''
    input.each {
        if (it.size() == 0)
            return

        def values = it.split(" ")
        def price = values[1] as BigDecimal
        output << values[0] + " " + decimalFormatter.format(price * plnToEurRate) + "\n"
    }
}

void Zadanie3()
{
    def dir = new File('C:\\Users\\Maciek\\IdeaProjects\\PJP\\pliki_html')
    def i = 1
    dir.eachFileRecurse {
        if (it.isFile() && it.name.endsWith('.html'))
            println i++ + " " + it
    }
}

Zadanie1()
Zadanie2()
Zadanie3()






