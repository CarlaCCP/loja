package br.com.tech.challenge.loja.core.applications.useCases.product

import br.com.tech.challenge.loja.core.domain.dto.product.ProductDTO
import br.com.tech.challenge.loja.core.domain.entity.product.Product
import br.com.tech.challenge.loja.core.applications.ports.ProductRepository
import br.com.tech.challenge.loja.core.domain.dto.product.ProductDTO.Companion.fromProduct
import br.com.tech.challenge.loja.core.domain.valueObject.category.Category
import mu.KotlinLogging
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

  private val log = KotlinLogging.logger {}
  fun updateProduct(product: ProductDTO, id: String): Product? {

    if (productRepository.findById(id) != null) {
      return productRepository.save(product.toProduct())
    }
    log.info { "Produto $id não encontrado" }
    return null
  }

  fun saveProduct(productDTO: ProductDTO) =
      productRepository.save(productDTO.toProduct())

  fun findProductByCategory(category: String) =
      productRepository.findByCategoria(Category.getByDescription(category)).map { fromProduct(it) }

  fun findAll() = productRepository.findAll().map { fromProduct(it) }

  fun deleteProduct(id: String) = productRepository.deleteById(id)

  @EventListener(ContextRefreshedEvent::class)
  fun insertProductsOnInit() {
    if (productRepository.findAll().isEmpty()) {
      productRepository.save(buildProduct1())
      productRepository.save(buildProduct2())
      productRepository.save(buildProduct3())
      productRepository.save(buildProduct4())
      log.info { "Produtos cadastrados com sucesso" }
    }
  }

  private fun buildProduct1() =
      Product(
          categoria = Category.LANCHE,
          nome = "Lanche 1",
          descricao = "Lanche 1 teste",
          preco = 30.0,
          imagem = "image.png"
      )

  private fun buildProduct2() =
      Product(
          categoria = Category.ACOMPANHAMENTO,
          nome = "Acompanhemento 1",
          descricao = "Acompanhamento 1 teste",
          preco = 15.0,
          imagem = "image.png"
      )

  private fun buildProduct3() =
      Product(
          categoria = Category.BEBIDA,
          nome = "Bebida 1",
          descricao = "Bebida 1 teste",
          preco = 5.0,
          imagem = "image.png"
      )

  private fun buildProduct4() =
      Product(
          categoria = Category.SOBREMESA,
          nome = "Sobremesa 1",
          descricao = "Sobremesa 1 teste",
          preco = 10.0,
          imagem = "image.png"
      )
}