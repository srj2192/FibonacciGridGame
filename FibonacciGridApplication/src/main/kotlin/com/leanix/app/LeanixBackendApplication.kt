package com.leanix.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LeanixBackendApplication

fun main(args: Array<String>) {
	runApplication<LeanixBackendApplication>(*args)
}
