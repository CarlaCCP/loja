package br.com.tech.challenge.loja.config

import br.com.tech.challenge.loja.usecase.OrderUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OrderConfig {
  @Bean
  fun orderUseCase(): OrderUseCase {
    return OrderUseCase()
  }

}
