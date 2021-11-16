package com.eslirodrigues.fruitmacro.data.retrofit

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.FileNotFoundException
import java.net.URL

@RunWith(JUnit4::class)
class FruitApiTest {

    private lateinit var service: FruitApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        service = Retrofit.Builder()
            .run {
                baseUrl(mockWebServer.url(""))
                addConverterFactory(MoshiConverterFactory.create(moshi))
                build()
            }.create(FruitApi::class.java)
    }

    @After
    fun close() {
        mockWebServer.shutdown()
    }


    @Test
    fun `should hit endpoints with expected parameters`() = runBlocking {
        mockWebServer.enqueue(MockResponse().setBody(readFile("allfruits.json")))

        val result = service.getAllFruits()

        val request = mockWebServer.takeRequest()

        assertThat(request.path).isEqualTo("/api/fruit/all")
        assertThat(result.size).isEqualTo(21)
    }


}

fun readFile(path: String): String {
    val content: URL? = ClassLoader.getSystemResource(path)

    return content?.readText() ?: throw FileNotFoundException("file path: $path was not found")
}