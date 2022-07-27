package tars.rooney.fleamarket.products.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(
    name = "products",
    indexes = [Index(columnList = "sellerId"),
        Index(columnList = "buyerId")]
)
class Product(
    @Id
    @GeneratedValue
    val id: Long,
    val name: String,
    val price: Long,
    val description: String,
    val sellerId: String,
    val buyerId: String?,
    val createdDate: LocalDateTime = LocalDateTime.now()
)