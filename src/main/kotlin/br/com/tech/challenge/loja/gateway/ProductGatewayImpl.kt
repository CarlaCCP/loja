package br.com.tech.challenge.loja.gateway

import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import br.com.tech.challenge.loja.core.valueObject.category.Category
import br.com.tech.challenge.loja.core.entity.product.Product
import com.mongodb.client.result.DeleteResult
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class ProductGatewayImpl(
    private val mongoTemplate: MongoTemplate
) : IProductGateway {

  override fun findAll(): List<Product> {
    return mongoTemplate.findAll(Product::class.java)
  }

  override fun findByCategoria(category: Category): List<Product> {
    return mongoTemplate.find(
        Query.query(
            Criteria.where("categoria").isEqualTo(category)
        ), Product::class.java
    )
  }

  override fun findById(id: String): Product? {
    return mongoTemplate.findById(id, Product::class.java)
  }

  override fun save(product: Product): Product {
    return mongoTemplate.save(product)
  }

  override fun deleteById(id: String): DeleteResult {
    return mongoTemplate.remove(
        Query.query(Criteria.where("id").isEqualTo(id)), Product::class.java
    )
  }

}