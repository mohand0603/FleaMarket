package tars.rooney.fleamarket.products.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import tars.rooney.fleamarket.products.dto.LoginDto
import tars.rooney.fleamarket.products.dto.RegisterDto
import tars.rooney.fleamarket.products.dto.ResponseMessage
import tars.rooney.fleamarket.products.entities.User
import tars.rooney.fleamarket.products.service.UserService
import javax.validation.Valid

@RestController
class UserController(
    private val userService: UserService
) {

    @PostMapping("user/register")
    fun register(
        @RequestBody
        @Valid
        registerDto: RegisterDto
    ): ResponseMessage<Any> {

        val result = userService.register(registerDto)

        return ResponseMessage(
            result = true,
            message = result,
            data = null
        )

    }

    @GetMapping("user/{id}")
    fun findById(@PathVariable id: String): ResponseMessage<User> {

        val user = userService.findById(id)
        var result = true

        if(user == null)
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
    ): ResponseMessage<User> {

        val user = userService.login(loginDto)

        return ResponseMessage(
            result = true,
            message = null,
            data = user
        )
    }

}