package br.com.tech.challenge.loja.adapter.driver

import br.com.tech.challenge.loja.core.domain.dto.client.ClientDTO
import br.com.tech.challenge.loja.core.applications.ports.ClientRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/client")
@Validated
class ClientController(
    private val clientRepository: ClientRepository
) {

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun createClient(@Valid @RequestBody client: ClientDTO) =
      clientRepository.save(client.toClient())

  @GetMapping("/{cpf}")
  @ResponseStatus(HttpStatus.OK)
  fun getClient(@Valid @PathVariable cpf: String) =
      clientRepository.findById(cpf)

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun getClients() =
      clientRepository.findAll()
}