package tars.rooney.fleamarket.products.controller

import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import tars.rooney.fleamarket.products.dto.LoginDto
import tars.rooney.fleamarket.products.dto.RegisterDto
import tars.rooney.fleamarket.products.dto.ResponseMessage
import tars.rooney.fleamarket.products.entities.User
import tars.rooney.fleamarket.products.exception.NotFoundException
import tars.rooney.fleamarket.products.service.TokenService
import tars.rooney.fleamarket.products.service.UserService
import java.time.Duration
import java.util.*
import javax.validation.Valid

@RestController
class UserController(
    private val userService: UserService,
    private val tokenService: TokenService
) {

    @PostMapping("user/register")
    fun register(
        @RequestBody
        @Valid
        registerDto: RegisterDto
    ): ResponseMessage {

        val data = userService.register(registerDto)
        return ResponseMessage(
            result = true,
            message = null,
            data = data
        )
    }

    @GetMapping("user/confirm/{token}")
    fun userConfirm(@PathVariable token: String): ResponseMessage {

        val responseMessage = try {
            val user = userService.userConfirm(token)
            ResponseMessage(
                result = true,
                message = "인증 성공",
                data = user
            )

        } catch (e: NotFoundException) {
            ResponseMessage(
                result = false,
                message = e.message,
                data = null
            )
        }

        return responseMessage

    }

    @GetMapping("user/{id}")
    fun findById(@PathVariable id: String): ResponseMessage {

        val user = userService.findById(id)
        var result = true

        if (user == null)
            result = false

        return ResponseMessage(
            result = result,
            message = null,
            data = user
        )
    }

    @PostMapping("user/login")
    fun login(
        @RequestBody
        @Valid
        loginDto: LoginDto
    ): ResponseEntity<ResponseMessage> {

        val user = userService.login(loginDto)

        val responseHeaders = HttpHeaders()

        if (user == null) {
            return ResponseEntity(
                ResponseMessage(
                    result = true,
                    message = "fail",
                    data = null
                ),
                responseHeaders,
                HttpStatus.FORBIDDEN
            )
        }

        val jwtToken = tokenService.generateToken(user)

        responseHeaders.set("Authorization", "Bearer $jwtToken")

        return ResponseEntity<ResponseMessage>(
            ResponseMessage(
                result = true,
                message = null,
                data = user
            ),
            responseHeaders,
            HttpStatus.CREATED
        )
    }

    @GetMapping("user/verify")
    fun userVerify(
        @RequestHeader("Authorization")
        token: String
    ): ResponseMessage {

        val result = tokenService.verifyToken(token)

        return ResponseMessage(
            result = true,
            message = "nice",
            data = null
        )

    }

}