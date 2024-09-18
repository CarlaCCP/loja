package br.com.tech.challenge.loja.core.valueObject.category


enum class Category(val description: String) {
  LANCHE("Lanche"),
  ACOMPANHAMENTO("Acompanhamento"),
  BEBIDA("Bebida"),
  SOBREMESA("Sobremesa")
  ;

  companion object {
    fun getByDescription(description: String) =
        entries.first {
          it.description == description
        }
  }
}