package tars.rooney.fleamarket.products.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tars.rooney.fleamarket.products.entities.User

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findFirstById(id: String): User?

}