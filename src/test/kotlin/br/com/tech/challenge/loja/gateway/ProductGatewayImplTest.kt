package br.com.tech.challenge.loja.gateway

import br.com.tech.challenge.loja.core.valueObject.category.Category
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import com.amazonaws.services.dynamodbv2.model.*
import io.mockk.every
import io.mockk.mockk

class ProductGatewayImplTest : FunSpec({

  lateinit var dynamoDBClient: AmazonDynamoDB
  lateinit var productGatewayImpl: ProductGatewayImpl


  beforeTest {
    dynamoDBClient = mockk<AmazonDynamoDB>()

    productGatewayImpl = ProductGatewayImpl(dynamoDBClient)

  }


  test("Should find product by category") {
    val queryRequest = QueryRequest()
      .withTableName("produto")
      .withIndexName("categoria-index")
      .withKeyConditionExpression("categoria = :categoria")
      .withExpressionAttributeValues(mapOf(":categoria" to AttributeValue().withS(Category.LANCHE.name)))

    every { dynamoDBClient.query(queryRequest) } returns
        QueryResult()
          .withItems(
            mapOf(
              "categoria" to AttributeValue(Category.LANCHE.name)
            )
          )

    shouldNotBeNull {
      productGatewayImpl.findByCategoria(Category.LANCHE)
    }
  }

})

