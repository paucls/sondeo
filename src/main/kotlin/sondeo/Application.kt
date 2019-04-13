package sondeo

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("sondeo")
                .mainClass(Application.javaClass)
                .start()
    }
}
