package tars.rooney.fleamarket.products.entities

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue
    val userNum: Long,
    val name: String,
    val nickName: String,
    val id: String,
    val pw: String,
    val phone: String,
    val birthday: LocalDate,
    val createdDate: LocalDateTime = LocalDateTime.now()
    )