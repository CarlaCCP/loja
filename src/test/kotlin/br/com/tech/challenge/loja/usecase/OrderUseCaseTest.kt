package br.com.tech.challenge.loja.usecase

import br.com.tech.challenge.loja.adapter.OrderAdapter
import br.com.tech.challenge.loja.adapter.OrderGetAdapter
import br.com.tech.challenge.loja.core.dto.OrderDTO
import br.com.tech.challenge.loja.core.dto.ProductDTO
import br.com.tech.challenge.loja.core.entity.product.Product
import br.com.tech.challenge.loja.core.valueObject.category.Category
import br.com.tech.challenge.loja.core.valueObject.status.Status
import br.com.tech.challenge.loja.interfaces.client.IOrderClient
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.*
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.time.LocalDateTime

class OrderUseCaseTest : FunSpec({

  lateinit var orderClient : IOrderClient
  lateinit var productRepository : IProductGateway
  lateinit var orderUseCase:  OrderUseCase

  beforeTest{
    orderClient = mockk<IOrderClient>()
    productRepository = mockk<IProductGateway>()
    orderUseCase = OrderUseCase()
  }

  val product = Product(
    "1",
    Category.LANCHE,
    "Lanche gostoso",
    "Lanche gostoso pra caramba",
    12.2,
    "url"
  )
  val productDto = ProductDTO(
    "1",
    "Lanche",
    "Lanche gostoso",
    "Lanche gostoso pra caramba",
    12.2,
    "url"
  )
  val orderRequest = OrderDTO(
    products = listOf(
      productDto
    )
  )

  test("Should create a order") {
    every { productRepository.findById("1") } returns product
    every { orderClient.createOrder(orderRequest) } returns OrderAdapter(
      null, null, null, null, null
    )
     shouldNotBeNull {
       orderUseCase.createOrder(orderClient, productRepository, orderRequest)
     }
  }


  test("should not create an order with not found products") {
    every { productRepository.findById("1") } returns null

    shouldThrow<NotFoundException> {
      orderUseCase.createOrder(orderClient, productRepository, orderRequest)
    }
  }

  test("should get order") {
    every { orderClient.getOrder("1") } returns OrderGetAdapter(
      "1", listOf(productDto), LocalDateTime.now(), 12.2, Status.EM_PREPARACAO.name,
    )
    shouldNotBeNull {
      orderUseCase.getOrder(orderClient, "1")
    }
  }
})