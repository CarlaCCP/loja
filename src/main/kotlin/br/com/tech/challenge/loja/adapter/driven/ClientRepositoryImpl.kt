package br.com.tech.challenge.loja.adapter.driven

import br.com.tech.challenge.loja.core.applications.ports.ClientRepository
import br.com.tech.challenge.loja.core.domain.entity.client.Client
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

@Repository
class ClientRepositoryImpl(

    private val mongoTemplate: MongoTemplate

) : ClientRepository {

  override fun save(client: Client): Client {
    return mongoTemplate.save(client)
  }

  override fun findById(cpf: String): Client? {
    return mongoTemplate.findById(cpf, Client::class.java)
  }

  override fun findAll(): List<Client> {
    return mongoTemplate.findAll(Client::class.java)
  }

}