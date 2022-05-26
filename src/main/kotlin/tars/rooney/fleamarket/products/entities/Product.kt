package tars.rooney.fleamarket.products.entities

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
class Product (
    @Id
    @GeneratedValue
    val id: Long,
    val name: String,
    val price: Long,
    val description: String,
    val sellerId: Long,
    val buyerId: Long?,
    val createdDate: LocalDateTime = LocalDateTime.now()
)