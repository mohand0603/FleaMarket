package tars.rooney.fleamarket.products.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import tars.rooney.fleamarket.products.dto.NewProductDto
import tars.rooney.fleamarket.products.entities.Product
import tars.rooney.fleamarket.products.repositories.ProductRepository
import tars.rooney.fleamarket.products.service.ProductService

@RestController
class ProductController(
    private val productService: ProductService
) {

    @PostMapping("products/new")
    fun newProduct(@RequestBody product: NewProductDto) {

        productService.saveProduct(product)

    }


    @GetMapping("products/{id}")
    fun findById(@PathVariable id: Long): Product? {



        return productService.findById(id)

    }

    @GetMapping("products/orderByDate")
    fun findByDate(): List<Product> {
        return productService.findByDate()
    }

}