package br.com.tech.challenge.loja.controller

import br.com.tech.challenge.loja.adapter.ProductAdapter
import br.com.tech.challenge.loja.config.ProductConfig
import br.com.tech.challenge.loja.core.dto.ProductDTO
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
class ProductController(

  private val productConfig: ProductConfig
) {

  private val log = KotlinLogging.logger {}

  fun updateProduct(
    productGateway: IProductGateway,
    product: ProductDTO,
    id: String
  ): ProductAdapter? {
    val response = productConfig.productUseCase().updateProduct(productGateway, product, id)
    if (response == null) {
      log.info { "Produto $id não encontrado" }
      return null
    }
    return ProductAdapter.fromProduct(response)
  }

  fun getProductByCategory(productGateway: IProductGateway, category: String): List<ProductAdapter> {
    val response = productConfig.productUseCase().getProductByCategory(
      productGateway,
      category
    )
    return response.map { ProductAdapter.fromProduct(it) }
  }

  fun getProducts(productGateway: IProductGateway): List<ProductAdapter> {
    val response = productConfig.productUseCase().getProducts(productGateway)
    return response.map { ProductAdapter.fromProduct(it) }
  }

  fun createProduct(productGateway: IProductGateway, productDTO: ProductDTO) : ProductAdapter {
    val response = productConfig.productUseCase().createProduct(productGateway, productDTO.toProduct())
    return ProductAdapter.fromProduct(response)
  }

  fun deleteProduct(productGateway: IProductGateway, id: String) =
    productConfig.productUseCase().deleteProduct(productGateway, id)
}