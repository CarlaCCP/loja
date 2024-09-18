package br.com.tech.challenge.loja.api

import br.com.tech.challenge.loja.controller.PaymentController
import br.com.tech.challenge.loja.interfaces.client.IPaymentWebhookClient
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pagamento")
@Validated
class PaymentApi(

  private val paymentClient: IPaymentWebhookClient,
  private val paymentController: PaymentController
) {

  @GetMapping("/{id}")
  fun getPayment(@PathVariable id: String) = paymentController.getPayment(paymentClient, id)
}