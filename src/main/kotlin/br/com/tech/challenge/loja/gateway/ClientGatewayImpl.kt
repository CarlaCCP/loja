package br.com.tech.challenge.loja.gateway

import br.com.tech.challenge.loja.interfaces.gateway.IClientGateway
import br.com.tech.challenge.loja.core.entity.client.Client
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

@Repository
class ClientGatewayImpl(

    private val mongoTemplate: MongoTemplate

) : IClientGateway {

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