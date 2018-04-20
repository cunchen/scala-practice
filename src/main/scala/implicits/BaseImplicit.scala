package implicits

import SimpleStateSalesTax._

object BaseImplicit {

    def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

    def calcTax2(amount: Float): Float = amount * implicitly[Float]

    def main(args: Array[String]): Unit = {
        val tax = calcTax(5000F)
        println(tax)

        val tax2 = calcTax2(5000F)
        println(tax2)

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
