package tars.rooney.fleamarket.products.service

import org.springframework.stereotype.Service
import tars.rooney.fleamarket.products.dto.LoginDto
import tars.rooney.fleamarket.products.dto.RegisterDto
import tars.rooney.fleamarket.products.entities.User
import tars.rooney.fleamarket.products.repositories.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun register(registerDto: RegisterDto): String {

        val duplicationId = userRepository.findFirstById(registerDto.id)

        if (duplicationId != null)
            return "id중복"

        with(registerDto) {
            userRepository.save(
                User(
                    id = id,
                    pw = pw,
                    name = name,
                    nickName = nickName,
                    phone = phone,
                )
            )
        }
        return "생성 성공"

    }

    fun findById(id: String): User? {
        return userRepository.findFirstById(id)
    }

    fun login(loginDto: LoginDto): User? {

        val realUser = userRepository.findFirstById(loginDto.id)

        if(realUser == null)
            return null

        if(realUser.pw != loginDto.pw)
            return null

        return realUser

    }

}