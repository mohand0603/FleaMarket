package tars.rooney.fleamarket.products.service

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import tars.rooney.fleamarket.products.dto.LoginDto
import tars.rooney.fleamarket.products.dto.RegisterDto
import tars.rooney.fleamarket.products.entities.Token
import tars.rooney.fleamarket.products.entities.User
import tars.rooney.fleamarket.products.exception.NotFoundException
import tars.rooney.fleamarket.products.repositories.TokenRepository
import tars.rooney.fleamarket.products.repositories.UserRepository
import java.time.Duration
import java.util.*
import javax.transaction.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) {

    fun register(registerDto: RegisterDto): String {

        val duplicationId = userRepository.findFirstById(registerDto.id)

        if (duplicationId != null)
            return "id중복"

        val user = User(
            id = registerDto.id,
            pw = registerDto.pw,
            name = registerDto.name,
            nickName = registerDto.nickName,
            phone = registerDto.phone
        )

        userRepository.save(user)

        val token = UUID.randomUUID().toString()
        tokenRepository.save(Token(token, user))
        return token

    }

    fun findById(id: String): User? {
        return userRepository.findFirstById(id)
    }

    fun login(loginDto: LoginDto): User? {

        val user = userRepository.findFirstById(loginDto.id)

        if (user == null)
            return null

        if (user.pw != loginDto.pw)
            return null

        return user

    }

    @Transactional
    fun userConfirm(token: String): User {
        val tokenUser = tokenRepository.findFirstById(token)
            ?: throw NotFoundException("토큰에 해당하는 유저를 찾지 못함")

        val user = tokenUser.user
        user.state = User.STATE.YES

        userRepository.save(user)

        tokenRepository.deleteById(token)

        return user
    }

}