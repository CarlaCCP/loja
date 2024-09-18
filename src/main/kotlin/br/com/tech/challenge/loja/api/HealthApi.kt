package br.com.tech.challenge.loja.api

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
@Validated
class HealthApi {

  private val log = KotlinLogging.logger {}

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  fun getHealth(): String {
    log.info { "Eu to benzinha (✿◠‿◠)" }
    return "Eu to benzinha (✿◠‿◠)"
  }
}