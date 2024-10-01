package br.com.tech.challenge.loja.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("aws")
data class AwsProperties(
  val accessKey: String,
  val secretKey: String,
  val region: String
)
