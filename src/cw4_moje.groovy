

void zadanie1(){

    def plik = new File('D:\\PJP\\Projekty.txt')
    def ProjektyDuze_file = new File('D:\\PJP\\ProjektyDuze_file.txt')
    def Programisci_file = new File('D:\\PJP\\Programisci_file.txt')

    plik.each {
        linia_split = it.split("\t")

        def line_projekt = linia_split[0]
        def line_programisci = linia_split.drop(1)

        def programista_projekt = [:].withDefault { [] }

        if(line_programisci.size() > 3)
            ProjektyDuze_file << line_projekt + "\n"

        line_programisci.each { programista_projekt[it] += line_projekt }

        programista_projekt.each { programmer, languages -> Programisci_file << programmer + "\t" + languages.join("\t") + "\n" }
    }

}

println "--------------- zadanie1 ---------------"
zadanie1()