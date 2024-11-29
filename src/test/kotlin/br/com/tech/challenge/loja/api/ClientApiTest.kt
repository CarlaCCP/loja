package br.com.tech.challenge.loja.api

import br.com.tech.challenge.loja.controller.ClientController
import br.com.tech.challenge.loja.core.dto.ClientDTO
import br.com.tech.challenge.loja.interfaces.gateway.IClientGateway
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockk

class ClientApiTest : FunSpec ({

  lateinit var clientGateway: IClientGateway
  lateinit var clientController: ClientController
  lateinit var clientApi: ClientApi

  beforeTest {
    clientGateway = mockk<IClientGateway>()
    clientController = mockk<ClientController>()

    clientApi = ClientApi(clientGateway, clientController)
  }

  val clientDto = ClientDTO(
    "46985278963",
    "Carla linda",
    "carla@teste.com"
  )

  test("Should create client") {
    every { clientController.saveClient(clientGateway, clientDto) } returns clientDto

    shouldNotBeNull {
      clientApi.createClient(clientDto)
    }
  }


  test("Should get client") {
    every { clientController.getClient(clientGateway, "46985278963") } returns clientDto

    shouldNotBeNull {
      clientApi.getClient("46985278963")
    }
  }


  test("Should get clients") {
    every { clientController.getClients(clientGateway) } returns listOf(clientDto)

    shouldNotBeNull {
      clientApi.getClients()
    }
  }
})