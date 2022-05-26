package tars.rooney.fleamarket.products.dto

class ResponseMessage<T> (

    val result: Boolean,
    val message: String?,
    val data: T?
    )