import javax.swing.JOptionPane

void zad4() {
    def result=[]
    def lista=["zero", "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"]
    def number=JOptionPane.showInputDialog("Podaj liczby")

    number.each{ result.add(lista[it.toInteger()] ) }
    println result.join(" - ")
}

void zad5() {
    def productsMap = [:].withDefault { 0 }
    def product = ''

    while (true) {
        product = JOptionPane.&showInputDialog("Podaj: nazwa produktu=cena", product)
        if (product == null || product.equals(""))
            break

        def productInfo = product.split("=")
        if (productInfo.size() == 2 && productInfo[1].isInteger()) {
            productsMap[productInfo[0]] += productInfo[1].toInteger()
            product = ''
        }
        else
            product = "Nie poprawna wartość: $product"
    }
    println productsMap
}

//zad4()
zad5()