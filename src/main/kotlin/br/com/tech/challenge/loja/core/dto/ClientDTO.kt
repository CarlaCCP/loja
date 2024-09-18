package br.com.tech.challenge.loja.core.dto

import br.com.tech.challenge.loja.core.entity.client.Client
import br.com.tech.challenge.loja.interfaces.dto.IClientDTO

data class ClientDTO(
    override val cpf: String? = null,
    val nome: String? = null,
    override val email: String? = null
) : IClientDTO {

  fun toClient() = Client(cpf, nome, email)
}