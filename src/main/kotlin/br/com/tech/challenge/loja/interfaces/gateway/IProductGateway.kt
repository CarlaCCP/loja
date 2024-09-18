package br.com.tech.challenge.loja.interfaces.gateway

import br.com.tech.challenge.loja.core.valueObject.category.Category
import br.com.tech.challenge.loja.core.entity.product.Product
import com.mongodb.client.result.DeleteResult
import org.springframework.stereotype.Repository

@Repository
interface IProductGateway {

  fun findAll(): List<Product>

  fun findByCategoria(category: Category): List<Product>

  fun findById(id: String): Product?

  fun save(product: Product): Product

  fun deleteById(id: String): DeleteResult
}