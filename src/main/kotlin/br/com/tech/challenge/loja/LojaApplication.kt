package br.com.tech.challenge.loja

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@EnableMongoRepositories
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
class LojaApplication

fun main(args: Array<String>) {
	runApplication<LojaApplication>(*args)
}
