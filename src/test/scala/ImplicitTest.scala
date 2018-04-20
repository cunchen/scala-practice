import SimpleStateSalesTax._

object ImplicitTest {

    def calcTax(amount: Float)(implicit rate: Float) = amount * rate

    def main(args: Array[String]): Unit = {
        val tax = calcTax(50000F)

        println(tax)
    }

}

object SimpleStateSalesTax {
    implicit val rate: Float = 0.05F
}

case class ComplicatedSalesTaxData(baseRate: Float,
                                   isTaxHoliday: Boolean,
                                   storeId: Int)

object ComplicatedSalesTax {
    private def extraTaxRateForStore(id: Int): Float = {
        0.0F
    }

    implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float = {
        if(cstd.isTaxHoliday) 0.0F
        else cstd.baseRate + extraTaxRateForStore(cstd.storeId)
    }
}
