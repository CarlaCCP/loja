package br.com.tech.challenge.loja.controller

import br.com.tech.challenge.loja.adapter.OrderAdapter
import br.com.tech.challenge.loja.config.OrderConfig
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import br.com.tech.challenge.loja.adapter.OrderGetAdapter
import br.com.tech.challenge.loja.core.dto.OrderDTO
import br.com.tech.challenge.loja.core.dto.PaymentDTO
import br.com.tech.challenge.loja.interfaces.client.IPaymentWebhookClient

import br.com.tech.challenge.loja.interfaces.gateway.IOrderGateway

import org.springframework.stereotype.Component

@Component
class OrderController(
  private val orderConfig: OrderConfig
) {

  fun createOrder(
    orderGateway: IOrderGateway,
    productRepository: IProductGateway,
    orderRequest: OrderDTO,
    paymentClient: IPaymentWebhookClient
  ): OrderAdapter {
    val response = orderConfig.orderUseCase().createOrder(orderGateway, productRepository, orderRequest, paymentClient)
    return OrderAdapter.fromOrderToSummary(response)
  }

  fun getOrder (orderGateway: IOrderGateway, id: String): OrderGetAdapter? {
    val response = orderConfig.orderUseCase().getOrder(orderGateway, id)
    return OrderGetAdapter.fromOrder(response)
  }

  fun getOrders(orderGateway: IOrderGateway): List<OrderGetAdapter?> {
    val response = orderConfig.orderUseCase().getOrders(orderGateway)
    return response.map { OrderGetAdapter.fromOrder(it) }
  }

  fun getOrderByStatus(orderGateway: IOrderGateway, string: String) : List<OrderGetAdapter>? {
    return orderConfig.orderUseCase().getOrderByStatus(orderGateway, string)?.map { OrderGetAdapter.fromOrder(it)!! }
  }
  fun deleteOrder(orderGateway: IOrderGateway, id: String) = orderConfig.orderUseCase().deleteOrder(
    orderGateway, id
  )

  fun updatePaymentOrder(orderGateway: IOrderGateway, paymentDTO: PaymentDTO) =
    orderConfig.orderUseCase().updatePaymentOrder(orderGateway, paymentDTO)
}