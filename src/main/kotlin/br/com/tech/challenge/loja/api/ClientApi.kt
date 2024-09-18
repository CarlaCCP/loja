package br.com.tech.challenge.loja.api

import br.com.tech.challenge.loja.controller.ClientController
import br.com.tech.challenge.loja.core.dto.ClientDTO
import br.com.tech.challenge.loja.interfaces.gateway.IClientGateway
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/client")
@Validated
class ClientApi(
  private val clientGateway: IClientGateway,
  private val clientController: ClientController
) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createClient(@Valid @RequestBody client: ClientDTO) = clientController.saveClient(clientGateway, client)

  @GetMapping("/{cpf}")
  @ResponseStatus(HttpStatus.OK)
  fun getClient(@Valid @PathVariable cpf: String) = clientController.getClient(clientGateway, cpf)

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun getClients() = clientController.getClients(clientGateway)
}