package br.com.tech.challenge.loja.controller

import br.com.tech.challenge.loja.adapter.OrderAdapter
import br.com.tech.challenge.loja.config.OrderConfig
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import br.com.tech.challenge.loja.adapter.OrderGetAdapter
import br.com.tech.challenge.loja.core.dto.OrderDTO
import br.com.tech.challenge.loja.interfaces.client.IOrderClient

import org.springframework.stereotype.Component

@Component
class OrderController(
  private val orderConfig: OrderConfig
) {

  fun createOrder(
    orderClient: IOrderClient,
    productRepository: IProductGateway,
    orderRequest: OrderDTO,
  ): OrderAdapter {
    return orderConfig.orderUseCase().createOrder(orderClient, productRepository, orderRequest)
  }

  fun getOrder(orderClient: IOrderClient, id: String): OrderGetAdapter? {
    val response = orderConfig.orderUseCase().getOrder(orderClient, id)
    return response
  }

}