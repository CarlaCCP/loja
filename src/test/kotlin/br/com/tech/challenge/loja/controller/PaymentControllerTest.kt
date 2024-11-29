package br.com.tech.challenge.loja.controller

import br.com.tech.challenge.loja.config.PaymentConfig
import br.com.tech.challenge.loja.interfaces.client.IPaymentWebhookClient
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockk

class PaymentControllerTest : FunSpec ({

  lateinit var paymentConfig: PaymentConfig
  lateinit var paymentClient: IPaymentWebhookClient
  lateinit var paymentController: PaymentController

  beforeTest {
    paymentConfig = mockk<PaymentConfig>()
    paymentClient = mockk<IPaymentWebhookClient>()

    paymentController = PaymentController(paymentConfig)
  }

  test("Should get payment") {
    every { paymentConfig.paymentUseCase().getPayment(paymentClient, "1") } returns "Pagamento confirmado"

    shouldNotBeNull {
      paymentController.getPayment(paymentClient, "1")
    }
  }
})