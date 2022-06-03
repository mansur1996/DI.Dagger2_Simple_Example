package com.example.dagger2_example.model

/**
 * Example without Manual DI
 */
class Engine {
    fun start() {
        println("Engine is working")
    }
}

class Car {
    private val engine = Engine()
    fun start() {
        engine.start()
    }
}

fun main() {
    val car = Car()
    car.start()
}

/**
 * Example with Manual DI
 */
class Car1(private val engine: Engine) {
    fun start() {
        engine.start()
    }
}

fun main1() {
    val engine = Engine()
    val car1 = Car1(engine)
    car1.start()
}

/**
 * Example2 with Manual DI
 */

object ServiceLocator {
    fun getEngine(): Engine = Engine()
}

class Car2 {
    private val engine = ServiceLocator.getEngine()
    fun start() {
        engine.start()
    }
}

fun main2() {
    val car2 = Car2()
    car2.start()
}