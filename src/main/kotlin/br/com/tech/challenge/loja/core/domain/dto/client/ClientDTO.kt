package br.com.tech.challenge.loja.core.domain.dto.client

import br.com.tech.challenge.loja.core.domain.entity.client.Client
import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.br.CPF

data class ClientDTO(
    @field:CPF(message = "Cpf inválido")
    val cpf: String? = null,
    val nome: String? = null,
    @field:Email(message = "Email invalido")
    val email: String? = null
) {

  fun toClient() = Client(cpf, nome, email)
}