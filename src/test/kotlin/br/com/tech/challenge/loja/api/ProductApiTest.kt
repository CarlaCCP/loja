package br.com.tech.challenge.loja.api

import br.com.tech.challenge.loja.adapter.ProductAdapter
import br.com.tech.challenge.loja.controller.ProductController
import br.com.tech.challenge.loja.core.dto.ProductDTO
import br.com.tech.challenge.loja.core.entity.product.Product
import br.com.tech.challenge.loja.core.valueObject.category.Category
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import com.amazonaws.services.dynamodbv2.document.DeleteItemOutcome
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockk

class ProductApiTest : FunSpec ({

  lateinit var productGateway: IProductGateway
  lateinit var productController: ProductController
  lateinit var productApi: ProductApi

  beforeTest {
    productGateway = mockk<IProductGateway>()
    productController = mockk<ProductController>()

    productApi = ProductApi(
      productGateway, productController
    )
  }


  val productDto = ProductDTO(
    "1",
    "Acompanhamento",
    "Lanche gostoso",
    "Lanche gostoso pra caramba",
    12.2,
    "url"
  )
  val productAdpater = ProductAdapter(
    "1",
    "Acompanhamento",
    "Lanche gostoso",
    "Lanche gostoso pra caramba",
    12.2,
    "url"
  )

  test("Should get product by category") {
    every {
      productController.getProductByCategory(productGateway, Category.LANCHE.name)
    } returns listOf(productAdpater)

    shouldNotBeNull {
      productApi.getProductByCategory(Category.LANCHE.name)
    }
  }

  test("Should get all products") {
    every {
      productController.getProducts(productGateway)
    } returns listOf(productAdpater)

    shouldNotBeNull {
      productApi.getProducts()
    }
  }

  test("Should create products") {
    every {
      productController.createProduct(productGateway, productDto)
    } returns productAdpater

    shouldNotBeNull {
      productApi.createProduct( productDto)
    }
  }

  test("Should updated products") {
    every {
      productController.updateProduct(productGateway, productDto, "1")
    } returns productAdpater

    shouldNotBeNull {
      productApi.updateProduct( productDto, "1")
    }
  }

  test("Should delete product") {
    every {
      productController.deleteProduct(productGateway, "1")
    } returns DeleteItemOutcome(DeleteItemResult())

    shouldNotBeNull {
      productApi.deleteProduct("1")
    }
  }
})