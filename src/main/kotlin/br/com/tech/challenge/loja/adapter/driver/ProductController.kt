package br.com.tech.challenge.loja.adapter.driver

import br.com.tech.challenge.loja.core.domain.dto.product.ProductDTO
import br.com.tech.challenge.loja.core.applications.useCases.product.ProductService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produto")
@Validated
class ProductController(

    private val productService: ProductService
) {

  @GetMapping("/{category}")
  @ResponseStatus(HttpStatus.OK)
  fun getProductByCategory(
      @PathVariable category: String
  ) = productService.findProductByCategory(category)

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun getAllProduct() = productService.findAll()

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createProduct(@RequestBody productDTO: ProductDTO) =
      productService.saveProduct(productDTO)

  @PostMapping("/atualiza-produto/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  fun updateProduct(
      @RequestBody productDTO: ProductDTO,
      @PathVariable id: String
  ) = productService.updateProduct(productDTO, id)

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  fun deleteProduct(@PathVariable id: String) = productService.deleteProduct(id)

}