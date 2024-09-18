package br.com.tech.challenge.loja.usecase

import br.com.tech.challenge.loja.interfaces.client.IPaymentWebhookClient

class PaymentUseCase {

  fun getPayment(paymentClient: IPaymentWebhookClient, id: String) =
    paymentClient.getPayment(id)?.event?.description
}