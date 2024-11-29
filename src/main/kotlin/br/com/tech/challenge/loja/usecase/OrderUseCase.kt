package br.com.tech.challenge.loja.usecase

import br.com.tech.challenge.loja.adapter.OrderAdapter
import br.com.tech.challenge.loja.adapter.OrderGetAdapter
import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import br.com.tech.challenge.loja.core.dto.OrderDTO
import br.com.tech.challenge.loja.interfaces.client.IOrderClient
import mu.KotlinLogging
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException

class OrderUseCase {

  private val log = KotlinLogging.logger {}
  fun getOrder(orderClient: IOrderClient, id: String): OrderGetAdapter = orderClient.getOrder(id)

  fun createOrder(
    orderClient: IOrderClient,
    productRepository: IProductGateway,
    orderRequest: OrderDTO,
  ): OrderAdapter {
    orderRequest.products.map {
      if (productRepository.findById(it.id!!) == null) {
        log.error { "Produtos não encontrados" }
        throw NotFoundException()
      }
    }
    return orderClient.createOrder(orderRequest)
  }

}
