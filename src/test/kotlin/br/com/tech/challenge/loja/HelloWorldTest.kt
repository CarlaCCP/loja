package br.com.tech.challenge.loja

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class HelloWorldTest {

  @Test
  @DisplayName("HELLOOOO")
  fun oi() {
    val expected = 42
    assert(expected == 42)
  }
}