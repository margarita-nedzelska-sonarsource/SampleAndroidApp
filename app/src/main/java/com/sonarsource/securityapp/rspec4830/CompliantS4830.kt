package com.sonarsource.securityapp.rspec4830


import okhttp3.*
import java.io.IOException
import java.util.*

class CompliantS4830() {

    companion object {
        fun okHttp() {
            val spec: ConnectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2) // Compliant (s4423)
                    .build()

            val client: OkHttpClient = OkHttpClient.Builder()
                    .connectionSpecs(Collections.singletonList(spec))
                    .build()

            val request = Request.Builder()
                    .url("https://self-signed.badssl.com/")
                    .build()

            val call: Call = client.newCall(request)

            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")

                        for ((name, value) in response.headers) {
                            println("$name: $value")
                        }

                    }
                }
            })
        }
    }
}

