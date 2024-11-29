package br.com.tech.challenge.loja.api

import br.com.tech.challenge.loja.adapter.OrderAdapter
import br.com.tech.challenge.loja.adapter.OrderGetAdapter
import br.com.tech.challenge.loja.controller.OrderController
import br.com.tech.challenge.loja.core.dto.OrderDTO
import br.com.tech.challenge.loja.core.dto.ProductDTO
import br.com.tech.challenge.loja.core.entity.order.Order
import br.com.tech.challenge.loja.core.entity.product.Product
import br.com.tech.challenge.loja.core.valueObject.category.Category
import br.com.tech.challenge.loja.core.valueObject.status.Status
import br.com.tech.challenge.loja.interfaces.client.IOrderClient
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime

class OrderApiTest : FunSpec ({

  lateinit var orderClient: IOrderClient
  lateinit var productGateway: IProductGateway
  lateinit var orderController: OrderController
  lateinit var orderApi: OrderApi

  beforeTest {
    orderClient = mockk<IOrderClient>()
    productGateway = mockk<IProductGateway>()
    orderController = mockk<OrderController>()

    orderApi = OrderApi(
      orderClient,
      productGateway,
      orderController
    )
  }

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
  val product = Product(
    "1",
    Category.LANCHE,
    "Lanche gostoso",
    "Lanche gostoso pra caramba",
    12.2,
    "url"
  )

  val order = OrderGetAdapter(
    id = "12",
    products = listOf(productDto),
    createdAt = LocalDateTime.now(),
    preco = 12.2,
    status = Status.EM_PREPARACAO.name
  )

  val orderAdapter = OrderAdapter.fromOrderToSummary(
    Order(
      id = "12",
      products = listOf(product),
      createdAt = LocalDateTime.now(),
      preco = 12.2,
      orderStatus = Status.EM_PREPARACAO
    )
  )

  test("Should get order") {
    every { orderController.getOrder(orderClient, "1") } returns order
    shouldNotBeNull {
      orderApi.getOrder("1")
    }
  }

  test("Should create order") {
    every { orderController.createOrder(orderClient, productGateway, orderRequest) } returns orderAdapter
    shouldNotBeNull {
      orderApi.createOrder(orderRequest)
    }
  }
})