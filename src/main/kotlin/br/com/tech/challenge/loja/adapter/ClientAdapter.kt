package br.com.tech.challenge.loja.adapter

import br.com.tech.challenge.loja.core.dto.ClientDTO
import br.com.tech.challenge.loja.core.entity.client.Client

data class ClientAdapter (
  val cpf: String? = null,
  val nome: String? = null,
  val email: String? = null
) {
  companion object {
    fun fromClient(client: Client?) =
      ClientDTO(
        client?.cpf,
        client?.nome,
        client?.email
      )
  }
}
