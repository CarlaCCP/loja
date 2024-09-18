package br.com.tech.challenge.loja.api

import br.com.tech.challenge.loja.controller.ProductController
import br.com.tech.challenge.loja.core.dto.ProductDTO
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produto")
@Validated
class ProductApi(
  private val productGateway: IProductGateway,
  private val productController: ProductController
) {

  @GetMapping("/{category}")
  @ResponseStatus(HttpStatus.OK)
  fun getProductByCategory(
    @PathVariable category: String
  ) = productController.getProductByCategory(productGateway, category)

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun getProducts() = productController.getProducts(productGateway)

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createProduct(@RequestBody productDTO: ProductDTO) = productController.createProduct(productGateway, productDTO)

  @PostMapping("/atualiza-produto/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  fun updateProduct(
    @RequestBody productDTO: ProductDTO,
    @PathVariable id: String
  ) = productController.updateProduct(productGateway, productDTO, id)

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  fun deleteProduct(@PathVariable id: String) = productController.deleteProduct(productGateway, id)

}