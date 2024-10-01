package br.com.tech.challenge.loja.interfaces.db

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "cliente")
interface IClient {
  @get:DynamoDBHashKey
  val cpf: String?
  @get:DynamoDBAttribute
  val nome: String?
  @get:DynamoDBAttribute
  val email: String?
}