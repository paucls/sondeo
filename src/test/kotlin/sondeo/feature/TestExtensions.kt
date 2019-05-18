import io.restassured.response.ResponseBodyExtractionOptions
import io.restassured.specification.RequestSpecification

fun RequestSpecification.whenever(): RequestSpecification {
    return this.`when`()
}

inline fun <reified T> ResponseBodyExtractionOptions.to(): T {
    return this.`as`(T::class.java)
}