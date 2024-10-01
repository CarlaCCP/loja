package br.com.tech.challenge.loja.gateway

import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import br.com.tech.challenge.loja.core.valueObject.category.Category
import br.com.tech.challenge.loja.core.entity.product.Product
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.document.Item
import com.amazonaws.services.dynamodbv2.document.Table
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.QueryRequest
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class ProductGatewayImpl(
  private val dynamoDB: AmazonDynamoDB

) : IProductGateway {

  private val tableName = "produto"
  private val dynamoDBClient = DynamoDB(dynamoDB)
  private val table: Table? = dynamoDBClient.getTable(tableName)
  override fun findAll(): List<Product>? {
    return table?.scan()?.map { itemToProduto(it) }
  }

  override fun findByCategoria(category: Category): List<Product>? {
    val queryRequest = QueryRequest()
      .withTableName(tableName)
      .withIndexName("categoria-index")
      .withKeyConditionExpression("categoria = :categoria")
      .withExpressionAttributeValues(mapOf(":categoria" to AttributeValue().withS(category.name)))
    val result = dynamoDB.query(queryRequest)
    return result.items.map {
      Product(
        id = it["id"]?.s,
        categoria = Category.valueOf(it["categoria"]?.s ?: ""),
        descricao = it["descricao"]?.s ?: "",
        imagem = it["imagem"]?.s ?: "",
        nome = it["nome"]?.s ?: "",
        preco = it["preco"]?.n?.toDouble() ?: 0.0
      )
    }
  }

  override fun findById(id: String): Product? {
    val item = table?.getItem("id", id)
    return item?.let { itemToProduto(it) }
  }

  override fun save(product: Product): Product {
    val item = Item()
      .withPrimaryKey("id", UUID.randomUUID().toString())
      .withString("categoria", product.categoria.name)
      .withString("nome", product.nome)
      .withString("descricao", product.descricao)
      .withDouble("preco", product.preco)
      .withString("imagem", product.imagem)
    table?.putItem(item)
    return product
  }

  override fun deleteById(id: String): DeleteItemOutcome? {
    return table?.deleteItem("id", id)
  }

  private fun itemToProduto(item: Item) =
    Product(
      id = item.getString("id"),
      nome = item.getString("nome"),
      categoria = Category.valueOf(item.getString("categoria")),
      descricao = item.getString("descricao"),
      preco = item.getDouble("preco"),
      imagem = item.getString("imagem"),
    )
}