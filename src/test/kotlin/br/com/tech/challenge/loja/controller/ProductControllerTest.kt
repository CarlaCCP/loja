package br.com.tech.challenge.loja.controller

import br.com.tech.challenge.loja.adapter.ProductAdapter
import br.com.tech.challenge.loja.config.ProductConfig
import br.com.tech.challenge.loja.core.dto.ProductDTO
import br.com.tech.challenge.loja.core.valueObject.category.Category
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ProductControllerTest : FunSpec ({

  lateinit var productConfig: ProductConfig
  lateinit var productGateway: IProductGateway
  lateinit var productController: ProductController

  beforeTest {
    productConfig = mockk<ProductConfig>()
    productGateway = mockk<IProductGateway>()

    productController = ProductController(productConfig)
  }

  val productDto = ProductDTO(
    "1",
    "Lanche",
    "Lanche gostoso",
    "Lanche gostoso pra caramba",
    12.2,
    "url"
  )

  test("Should update product"){
    every {
      productConfig
        .productUseCase()
        .updateProduct(
          productGateway,
          productDto, "1"
        )
    } returns productDto.toProduct()

    shouldNotBeNull {
      productController.updateProduct(
        productGateway,
        productDto,
        "1"
      )
    }
  }

  test("Should not update product if not found"){
    every {
      productConfig
        .productUseCase()
        .updateProduct(
          productGateway,
          productDto, "1"
        )
    } returns null

    productController.updateProduct(
        productGateway,
        productDto,
        "1"
      ) shouldBe null

  }


  test("Should get product by category"){
    every {
      productConfig
        .productUseCase()
        .getProductByCategory(
          productGateway,
          Category.LANCHE.name
        )
    } returns listOf(productDto.toProduct())

    shouldNotBeNull {
      productController.getProductByCategory(
        productGateway,
        Category.LANCHE.name
      )
    }
  }

  test("Should get all products"){
    every {
      productConfig
        .productUseCase()
        .getProducts( productGateway )
    } returns listOf(productDto.toProduct())

    shouldNotBeNull {
      productController.getProducts( productGateway)
    }
  }

  test("Should create product"){
    every {
      productConfig
        .productUseCase()
        .createProduct( productGateway , productDto.toProduct())
    } returns productDto.toProduct()

    shouldNotBeNull {
      productController.createProduct( productGateway, productDto)
    }
  }

  test("Should delete product by id"){
    every {
      productConfig
        .productUseCase()
        .deleteProduct( productGateway , "12")
    } returns DeleteItemOutcome(DeleteItemResult())

    shouldNotBeNull {
      productController.deleteProduct( productGateway, "12")
    }
  }
})