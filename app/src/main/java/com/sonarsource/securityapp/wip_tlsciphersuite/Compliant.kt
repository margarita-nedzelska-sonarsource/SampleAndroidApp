package com.sonarsource.securityapp.wip_tlsserversuite


import okhttp3.*
import java.io.IOException
import java.util.*


class Compliant() {

    companion object {
        fun okHttp() {
            val spec: ConnectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .cipherSuites(
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256) // Compliant (the tree cipher suite are recommended)
                    .build()

            val client: OkHttpClient = OkHttpClient.Builder()
                    .connectionSpecs(Collections.singletonList(spec))
                    .build()

            val request = Request.Builder()
                    .url("https://tls-v1-2.badssl.com:1012/")
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