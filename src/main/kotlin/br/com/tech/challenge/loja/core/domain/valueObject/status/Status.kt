package br.com.tech.challenge.loja.core.domain.valueObject.status

enum class Status(val description: String) {
  RECEBIDO("Recebido"),
  EM_PREPARACAO("Em preparação"),
  PRONTO("Pronto"),
  FINALIZADO("Finalizado")
  ;

  companion object {

    fun getByDescription(description: String) =
        entries.firstOrNull {
          description == it.description
        }
  }
}