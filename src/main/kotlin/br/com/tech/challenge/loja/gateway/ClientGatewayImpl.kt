package br.com.tech.challenge.loja.gateway

import br.com.tech.challenge.loja.interfaces.gateway.IClientGateway
import br.com.tech.challenge.loja.core.entity.client.Client
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.document.DynamoDB

import com.amazonaws.services.dynamodbv2.document.Item
import org.springframework.stereotype.Repository

@Repository
class ClientGatewayImpl(

  private val dynamoDB: AmazonDynamoDB
) : IClientGateway {

  private val tableName = "cliente"
  private val dynamoDBClient = DynamoDB(dynamoDB)
  private val table: Table? = dynamoDBClient.getTable(tableName)

  override fun save(client: Client): Client {
    val item = Item()
      .withPrimaryKey("cpf", client.cpf)
      .withString("nome", client.nome)
      .withString("email", client.email)
    table?.putItem(item)
    return client
  }

  override fun findById(cpf: String): Client? {
    val item = table?.getItem("cpf", cpf)
      return Client (
        cpf = item?.getString("cpf"),
        nome = item?.getString("nome"),
        email = item?.getString("email")
      )
  }

  override fun findAll(): List<Client>? {
    return table?.scan()
      ?.map {
        Client(
          cpf = it.getString("cpf"),
          nome = it.getString("nome"),
          email = it.getString("email")
        )
      }
  }

}