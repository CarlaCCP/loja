package br.com.tech.challenge.loja.interfaces.db

import br.com.tech.challenge.loja.core.entity.product.Product
import br.com.tech.challenge.loja.core.valueObject.status.Status
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import java.time.LocalDateTime

@DynamoDBTable(tableName = "pedido")
interface IOrder {
  @get:DynamoDBHashKey
  var id: String?
  @get:DynamoDBAttribute
  var products: List<Product>
  @get:DynamoDBAttribute
  var createdAt: LocalDateTime?
  @get:DynamoDBAttribute
  var preco: Double
  @get:DynamoDBAttribute
  var orderStatus: Status?
}