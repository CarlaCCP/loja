package br.com.tech.challenge.loja.config

import br.com.tech.challenge.loja.core.valueObject.status.Status
import br.com.tech.challenge.loja.interfaces.gateway.IOrderGateway
import br.com.tech.challenge.loja.usecase.OrderUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled

@Configuration
class OrderConfig(
  private val orderGateway: IOrderGateway,
) {
  @Bean
  fun orderUseCase(): OrderUseCase {
    return OrderUseCase()
  }

  @Scheduled(fixedRate = 120000)
  fun deleteOrderByStatus() {
    OrderUseCase().deleteOrderByStatus(orderGateway)
  }

  @Scheduled(fixedDelay = 120000)
  fun updateStatusPreparacao() {
    OrderUseCase().updateOrderByStatus(orderGateway, Status.EM_PREPARACAO)
  }

  @Scheduled(fixedDelay = 120000)
  fun updateStatusFinalizado() {
    OrderUseCase().updateOrderByStatus(orderGateway, Status.FINALIZADO)
  }

  @Scheduled(fixedDelay = 120000)
  fun updateStatusRecebido() {
    OrderUseCase().updateOrderByStatus(orderGateway, Status.RECEBIDO)
  }
}
