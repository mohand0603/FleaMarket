package tars.rooney.fleamarket.products.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tars.rooney.fleamarket.products.entities.Token

@Repository
interface TokenRepository: JpaRepository<Token, String> {

    fun findFirstById(id: String): Token?

}