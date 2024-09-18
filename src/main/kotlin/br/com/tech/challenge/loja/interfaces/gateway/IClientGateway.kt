package br.com.tech.challenge.loja.interfaces.gateway

import br.com.tech.challenge.loja.core.entity.client.Client
import org.springframework.stereotype.Repository

@Repository
interface IClientGateway {
  fun save(client: Client): Client

  fun findById(cpf: String): Client?

  fun findAll(): List<Client>
}