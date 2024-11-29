package br.com.tech.challenge.loja.api

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.nulls.shouldNotBeNull

class HealthApiTest : FunSpec ({

  lateinit var healthApi: HealthApi

  beforeTest {
    healthApi = HealthApi()
  }

  test("HealthApi") {
    shouldNotBeNull {
      healthApi.getHealth()
    }
  }
})