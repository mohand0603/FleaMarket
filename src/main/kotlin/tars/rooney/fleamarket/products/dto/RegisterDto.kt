package tars.rooney.fleamarket.products.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


data class RegisterDto (
    @field:NotBlank(message = "id가 없습니다.")
    @field:Size(min = 4, max = 12, message = "id는 4~12자 이어야 합니다.")
    val id: String,
    @field:NotBlank(message = "pw가 없습니다.")
    @field:Size(min = 4, max = 12, message = "pw는 4~12자 이어야 합니다.")
    val pw: String,
    @field:NotBlank(message = "이름이 없습니다.")
    val name: String,
    @field:NotBlank(message = "닉네임이 없습니다.")
    val nickName: String,
    @field:NotBlank(message = "핸드폰 번호가 없습니다.")
    val phone: String,
    )