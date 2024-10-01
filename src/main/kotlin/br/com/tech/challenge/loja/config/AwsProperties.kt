package br.com.tech.challenge.loja.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@ConfigurationProperties("aws")
data class AwsProperties (

  val accessKey: String,
  val secretKey: String,
  val sessionToken: String,
//  val region: String
){
}