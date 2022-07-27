package tars.rooney.fleamarket.products.common

import io.jsonwebtoken.SignatureException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import tars.rooney.fleamarket.products.dto.ResponseMessage

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(value = [SignatureException::class])
    fun signatureExceptionHandler(e: SignatureException): ResponseEntity<ResponseMessage> {
        val body = ResponseMessage(
            result = false,
            message = "토큰이 유효하지 않습니다.",
            data = null
        )
        return ResponseEntity(
            body,
            HttpStatus.UNAUTHORIZED
        )
    }
}