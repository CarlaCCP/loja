package br.com.tech.challenge.loja.interfaces.db

import br.com.tech.challenge.loja.core.valueObject.category.Category
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import org.springframework.data.annotation.Id

@DynamoDBTable(tableName = "produto")
interface IProduct {
  @get:DynamoDBHashKey
  @get:Id
  var id: String?
  @get:DynamoDBIndexHashKey(globalSecondaryIndexName = "categoria-index")
  var categoria: Category
  @get:DynamoDBAttribute
  var nome: String
  @get:DynamoDBAttribute
  var descricao: String
  @get:DynamoDBAttribute
  var preco: Double
  @get:DynamoDBAttribute
  var imagem: String
}