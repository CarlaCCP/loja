package br.com.tech.challenge.loja.api

import br.com.tech.challenge.loja.controller.PaymentController
import br.com.tech.challenge.loja.interfaces.client.IPaymentWebhookClient
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockk

class PaymentApiTest : FunSpec ({

  lateinit var paymentClient: IPaymentWebhookClient
  lateinit var paymentController: PaymentController
  lateinit var paymentApi: PaymentApi

  beforeTest {
    paymentClient = mockk<IPaymentWebhookClient>()
    paymentController = mockk<PaymentController>()

    paymentApi = PaymentApi(
      paymentClient,
      paymentController
    )
  }

  test("Should get payment") {
    every { paymentController.getPayment(paymentClient, "1") } returns ""
    shouldNotBeNull {
      paymentApi.getPayment("1")
    }
  }

})