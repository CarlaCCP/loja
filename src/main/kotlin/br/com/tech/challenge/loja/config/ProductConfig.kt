package br.com.tech.challenge.loja.config

import br.com.tech.challenge.loja.interfaces.gateway.IProductGateway
import br.com.tech.challenge.loja.usecase.ProductUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener

@Configuration
class ProductConfig(
  private val productGateway: IProductGateway
) {
  @Bean
  fun productUseCase() : ProductUseCase {
    return ProductUseCase()
  }

  @EventListener(ContextRefreshedEvent::class)
  fun insertProductsOnInit() {
    ProductUseCase().insertProductsOnInit(productGateway)
  }

}