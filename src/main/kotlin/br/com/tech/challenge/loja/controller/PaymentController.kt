package br.com.tech.challenge.loja.controller

import br.com.tech.challenge.loja.config.PaymentConfig
import br.com.tech.challenge.loja.interfaces.client.IPaymentWebhookClient
import org.springframework.stereotype.Component

@Component
class PaymentController(
  private val paymentConfig: PaymentConfig
) {

  fun getPayment(
    paymentClient: IPaymentWebhookClient,
    id: String
  ) = paymentConfig.paymentUseCase().getPayment(
    paymentClient, id
  )
}