package br.com.tech.challenge.loja.usecase

import br.com.tech.challenge.loja.core.dto.ProductDTO
import br.com.tech.challenge.loja.core.entity.product.Product
import br.com.tech.challenge.loja.core.valueObject.category.Category
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ProductUseCaseTest : FunSpec({

  lateinit var productGateway: IProductGateway
  lateinit var productUseCase: ProductUseCase

  beforeTest {
    productGateway = mockk<IProductGateway>()
    productUseCase = ProductUseCase()
  }

  val product = Product(
    "1",
    Category.LANCHE,
    "Lanche gostoso",
    "Lanche gostoso pra caramba",
    12.2,
    "url"
  )
  val productDtoUpdated = ProductDTO(
    "1",
    "Acompanhamento",
    "Lanche gostoso",
    "Lanche gostoso pra caramba",
    12.2,
    "url"
  )

  test("Should update product") {
    every { productGateway.findById("1") } returns product
    every { productGateway.save(productDtoUpdated.toProduct()) } returns product.copy(categoria = Category.ACOMPANHAMENTO)

    shouldNotBeNull {
      productUseCase.updateProduct(productGateway, productDtoUpdated, "1")
    }
    val result = productUseCase.updateProduct(productGateway, productDtoUpdated, "1")
    result?.categoria shouldBe Category.ACOMPANHAMENTO
  }


  test("Should not update product with not found product") {
    every { productGateway.findById("1") } returns null

    productUseCase.updateProduct(productGateway, productDtoUpdated, "1")
    shouldNotBeNull()

  }

  test("Should create product") {
    every { productGateway.save(product) } returns product
    shouldNotBeNull {
      productUseCase.createProduct(productGateway, product)
    }
  }

  test("Should return product by category") {
    every { productGateway.findByCategoria(Category.LANCHE) } returns listOf(product)
    shouldNotBeNull {
      productUseCase.getProductByCategory(productGateway, Category.LANCHE.description)
    }
    val result = productUseCase.getProductByCategory(productGateway, Category.LANCHE.description)
    result?.map {
      it.categoria shouldBe Category.LANCHE
    }
  }

  test("Should get products") {
    every { productGateway.findAll() } returns listOf(product)
    shouldNotBeNull {
      productUseCase.getProducts(productGateway)
    }
  }


  test("Should delete product by id") {
    every { productGateway.deleteById("1") } returns DeleteItemOutcome(DeleteItemResult())
    shouldNotBeNull {
      productUseCase.deleteProduct(productGateway, "1")
    }
  }

  test("Should not save product on init application") {
    val products = Product.buildStaticProducts()
    every { productGateway.findAll() } returns null
    products.map {
      every { productGateway.save(it) } returns it
    }
    shouldNotBeNull {
      productUseCase.insertProductsOnInit(productGateway, products)
    }

  }

})