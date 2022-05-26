package tars.rooney.fleamarket.products.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tars.rooney.fleamarket.products.entities.Product

@Repository
interface ProductRepository : JpaRepository<Product, Long>{

    fun findByName(name: String): List<Product>

    fun findByPriceBetween(low: Long, height: Long): List<Product>

    fun findTop2ByOrderByCreatedDateDesc(): List<Product>

}