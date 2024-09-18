package br.com.tech.challenge.loja.config

import br.com.tech.challenge.loja.usecase.PaymentUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PaymentConfig {

  @Bean
  fun paymentUseCase() : PaymentUseCase {
    return PaymentUseCase()
  }
}