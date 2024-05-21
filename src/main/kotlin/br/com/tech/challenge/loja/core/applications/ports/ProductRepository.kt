package br.com.tech.challenge.loja.core.applications.ports

import br.com.tech.challenge.loja.core.domain.valueObject.category.Category
import br.com.tech.challenge.loja.core.domain.entity.product.Product
import com.mongodb.client.result.DeleteResult
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository {

  fun findAll(): List<Product>

  fun findByCategoria(category: Category): List<Product>

  fun findById(id: String): Product?

  fun save(product: Product): Product

  fun deleteById(id: String): DeleteResult
}