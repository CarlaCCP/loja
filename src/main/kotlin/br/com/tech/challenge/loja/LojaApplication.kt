package br.com.tech.challenge.loja

import br.com.tech.challenge.loja.config.AwsProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@EnableConfigurationProperties(AwsProperties::class)
class LojaApplication

fun main(args: Array<String>) {
	runApplication<LojaApplication>(*args)
}
