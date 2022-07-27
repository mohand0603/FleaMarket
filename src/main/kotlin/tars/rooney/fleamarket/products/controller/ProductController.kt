package tars.rooney.fleamarket.products.controller

import org.springframework.web.bind.annotation.*
import tars.rooney.fleamarket.products.dto.BuyProductDto
import tars.rooney.fleamarket.products.dto.NewProductDto
import tars.rooney.fleamarket.products.dto.ResponseMessage
import tars.rooney.fleamarket.products.entities.Product
import tars.rooney.fleamarket.products.exception.ProductInfoAndTokenDifferentException
import tars.rooney.fleamarket.products.service.ProductService
import tars.rooney.fleamarket.products.service.TokenService

@RestController
class ProductController(
    private val productService: ProductService,
    private val tokenService: TokenService
) {

    @PostMapping("products/new")
    fun newProduct(
        @RequestHeader("Authorization") token: String,
        @RequestBody product: NewProductDto
    ): ResponseMessage {

        val result = tokenService.verifyToken(token)

        if (result.first != product.sellerId || result.second != product.name)
            throw ProductInfoAndTokenDifferentException("토큰의 정보와 유저 정보가 일치하지 않습니다.")

        productService.saveProduct(product)

        return ResponseMessage(
            result = true,
            message = "등록 완료",
            data = null
        )
    }


    @GetMapping("products/{id}")
    fun findById(@PathVariable id: Long): Product? {
        return productService.findById(id)

    }

    @GetMapping("products/orderByDate")
    fun findByDate(): List<Product> {
        return productService.findByDate()
    }

    @PostMapping("products/buy")
    fun buyProduct(
        @RequestHeader("Authorization") token: String,
        @RequestBody buyProductDto: BuyProductDto,
    ): ResponseMessage {

        val result = tokenService.verifyToken(token)

        if(result.first != buyProductDto.buyerId)
            return ResponseMessage(
                result = false,
                message = "토큰과 정보가 일치하지 않습니다.",
                data = null
            )

        productService.buyProduct(buyProductDto)

    }

}