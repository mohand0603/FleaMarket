package tars.rooney.fleamarket.products.dto

data class NewProductDto (

    val name: String,
    val price: Long,
    val description: String,
    val sellerId: Long
    )