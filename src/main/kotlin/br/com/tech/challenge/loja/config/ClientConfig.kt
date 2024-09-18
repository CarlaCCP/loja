package br.com.tech.challenge.loja.config

import br.com.tech.challenge.loja.usecase.ClientUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientConfig {
  @Bean
  fun clientUseCase(): ClientUseCase {
    return ClientUseCase()
  }
}