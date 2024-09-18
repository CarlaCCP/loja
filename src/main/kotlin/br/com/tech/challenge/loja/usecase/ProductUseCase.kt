package br.com.tech.challenge.loja.usecase

import br.com.tech.challenge.loja.core.dto.ProductDTO
import br.com.tech.challenge.loja.core.entity.product.Product
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import br.com.tech.challenge.loja.core.valueObject.category.Category
import mu.KotlinLogging

class ProductUseCase {

  private val log = KotlinLogging.logger {}
  fun updateProduct(
    productGateway: IProductGateway,
    product: ProductDTO,
    id: String
  ): Product? {

    if (productGateway.findById(id) != null) {
      return productGateway.save(
        product.toProduct()
      )
    }
    return null
  }

  fun createProduct(productGateway: IProductGateway, product: Product) =
    productGateway.save(product)

  fun getProductByCategory(productGateway: IProductGateway, category: String) =
    productGateway.findByCategoria(Category.getByDescription(category)).map { it }

  fun getProducts(productGateway: IProductGateway) = productGateway.findAll().map { it }

  fun deleteProduct(productGateway: IProductGateway, id: String) = productGateway.deleteById(id)

  fun insertProductsOnInit(productGateway: IProductGateway) {
    if (productGateway.findAll().isEmpty()) {
      Product.buildStaticProducts().map {
        productGateway.save(it)
      }
      log.info { "Produtos cadastrados com sucesso" }
    }
  }

}