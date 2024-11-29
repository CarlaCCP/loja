package br.com.tech.challenge.loja.controller

import br.com.tech.challenge.loja.config.ClientConfig
import br.com.tech.challenge.loja.core.dto.ClientDTO
import br.com.tech.challenge.loja.interfaces.gateway.IClientGateway
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.every
import io.mockk.mockk

class ClientControllerTest : FunSpec({

  lateinit var clientConfig: ClientConfig
  lateinit var clientGateway: IClientGateway
  lateinit var clientController: ClientController

  beforeTest {
    clientConfig = mockk<ClientConfig>()
    clientGateway = mockk<IClientGateway>()

    clientController = ClientController(clientConfig)
  }

  val clientDto = ClientDTO(
    "46985278963",
    "Carla linda",
    "carla@teste.com"
  )

  test("Should save client") {
    every {
      clientConfig
        .clientUseCase()
        .saveClient(
          clientGateway, clientDto.toClient()
        )
    } returns clientDto.toClient()

    shouldNotBeNull {
      clientController.saveClient(
        clientGateway,
        clientDto
      )
    }
  }

  test("Should get client") {
    every {
      clientConfig
        .clientUseCase()
        .getClient(
          clientGateway, "1"
        )
    } returns clientDto.toClient()

    shouldNotBeNull {
      clientController.getClient(
        clientGateway,
        "1"
      )
    }
  }


  test("Should all clients") {
    every {
      clientConfig
        .clientUseCase()
        .getClients(clientGateway)
    } returns listOf(clientDto.toClient())

    shouldNotBeNull {
      clientController.getClients(clientGateway)
    }
  }

})