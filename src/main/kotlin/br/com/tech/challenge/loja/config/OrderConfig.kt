package br.com.tech.challenge.loja.config

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
  fun updateStatus() {
    OrderUseCase().updateOrderByStatus(orderGateway)
  }
}
