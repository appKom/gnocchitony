package com.example.autobank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
class AutobankApplication

fun main(args: Array<String>) {
    runApplication<AutobankApplication>(*args)
}
