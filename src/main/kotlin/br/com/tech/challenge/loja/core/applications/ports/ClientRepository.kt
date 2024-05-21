package br.com.tech.challenge.loja.core.applications.ports

import br.com.tech.challenge.loja.core.domain.entity.client.Client
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository {
  fun save(client: Client): Client

  fun findById(cpf: String): Client?

  fun findAll(): List<Client>
}