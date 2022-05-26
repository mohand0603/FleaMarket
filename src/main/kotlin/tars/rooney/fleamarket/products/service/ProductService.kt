package tars.rooney.fleamarket.products.service

import org.springframework.stereotype.Service
import tars.rooney.fleamarket.products.dto.NewProductDto
import tars.rooney.fleamarket.products.entities.Product
import tars.rooney.fleamarket.products.repositories.ProductRepository

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    fun saveProduct(product: NewProductDto) {
        with(product) {
            productRepository.save(Product(0, name, price, description, sellerId, null))
        }
    }

    fun findById(id: Long): Product {
        return productRepository.findById(id).get()
    }

    fun findByDate(): List<Product> {
        return productRepository.findTop2ByOrderByCreatedDateDesc()
    }

}