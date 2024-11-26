package br.com.tech.challenge.loja.controller

import br.com.tech.challenge.loja.adapter.OrderAdapter
import br.com.tech.challenge.loja.adapter.OrderGetAdapter
import br.com.tech.challenge.loja.config.OrderConfig
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

class OrderControllerTest : FunSpec ({

  lateinit var orderConfig: OrderConfig
  lateinit var orderClient: IOrderClient
  lateinit var productRepository: IProductGateway
  lateinit var orderController: OrderController


  beforeTest {
    orderConfig = mockk<OrderConfig>()
    orderClient = mockk<IOrderClient>()
    productRepository = mockk<IProductGateway>()

    orderController = OrderController(orderConfig)


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

  test("Should create order") {
    every { orderConfig
      .orderUseCase()
      .createOrder(
        orderClient,
        productRepository,
        orderRequest
      )
    } returns OrderAdapter.fromOrderToSummary(
      Order(
        id = "12",
        products = listOf(product),
        createdAt = LocalDateTime.now(),
        preco = 12.2,
        orderStatus = Status.EM_PREPARACAO
      )
    )

    shouldNotBeNull {
      orderController.createOrder(
        orderClient,
        productRepository,
        orderRequest
      )
    }
  }


  test("Should get order") {
    every { orderConfig.orderUseCase().getOrder(orderClient, "1")
    } returns OrderGetAdapter(
        id = "12",
        products = listOf(productDto),
        createdAt = LocalDateTime.now(),
        preco = 12.2,
        status = Status.EM_PREPARACAO.name
      )


    shouldNotBeNull {
      orderController.getOrder(orderClient, "1")
    }
  }
})