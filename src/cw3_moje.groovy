import groovy.json.JsonSlurper

import java.text.DateFormat
import java.text.SimpleDateFormat


void zadanie1(){
    def lista1=['a', 'b', 'c']
    def lista2=[1, 8, 23]

    def lista1_2=lista1.collect{
        it * 2
    }

    def  lista2_2=lista2.collect {
        it * 2
    }
    println "--------------- zadanie1 ---------------"
    println "--------------- A ---------------"
    lista1.each {println it}
    lista2.each {println it}

    println "--------------- B ---------------"
    lista1_2.each {println it}
    lista2_2.each {println it}
}

void zadanie2(){

    def pmap = [ Groovy: [ 'Asia', 'Jan' ],
                 Grails: [ 'Asia', 'Jan', 'Stefan', 'Mirek' ],
                 Java: [ 'Asia', 'Stefan', 'Mirek' ],
                 JEE: [ 'Slawek', 'Stefan', 'Janusz' ]
    ]
    println "--------------- zadanie2 ---------------"
    println "--------------- A ---------------"
    pmap.each {k, v -> println "PROJEKT: " + k + "ILOSC: " + v.size()}

    println "--------------- B ---------------"
    pmap.findAll {k, v -> v.size >2 }.each {println it}

    println "--------------- C ---------------"
    def mapa_programista_projekt=[:].withDefault {[]}
    pmap.each {k, v -> v.each { mapa_programista_projekt[it] += k}}
    mapa_programista_projekt.each {k, v -> println "PROGRAMISTA: " + k + " PROJEKT: " + v}


}


void zadanie3(){

    def random_date = "sdasfasfasda3465 2018-12-01 asdzx2017-01-01cq2018-13-01"
    def myMatcher = random_date =~/(\d+){4}-(\d+){2}-(\d+){2}/

    println "--------------- zadanie3 ---------------"

    for (i in myMatcher){
        println i[0]
        def DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.lenient = false
        try {
            Date date = df.parse(i[0].toString());
            println "Parsable date"
        } catch (e) {
            println "NOT Parsable date"
        }
    }




}
BigDecimal getCurrentPlnToEuroRate()
{
    def euroToPln = (new JsonSlurper()).parse(new URL('http://api.nbp.pl/api/exchangerates/rates/A/EUR?format=json'))["rates"][0]["mid"]
    return 1 / euroToPln
}
void zadanie4(){
    def random_text = "sdas123.1fasfasda3465asdz0.5xcq1"
    def myMatcher = random_text =~/((\+|-)?([0-9]+)([.][0-9]+)?)/


    println "--------------- zadanie4 ---------------"
    println "--------------- A ---------------"
    myMatcher.each {println it[0]}

    println "--------------- B ---------------"
    println "AKTUALNY MNOŻNIK KURSU: " + getCurrentPlnToEuroRate()
    def mnoznik = getCurrentPlnToEuroRate()

    for (i in myMatcher){
        a = i[0] as BigDecimal
        println "mnoznik: " + mnoznik
        println a * mnoznik + " EUR"

    }

}
zadanie1()
zadanie2()
zadanie3()
zadanie4()