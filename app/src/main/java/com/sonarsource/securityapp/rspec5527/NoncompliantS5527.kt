package com.sonarsource.securityapp.rspec5527


import okhttp3.*
import java.io.IOException
import javax.net.ssl.*

class NoncompliantS5527() {

    companion object {
        fun okHttp() {
            val builder = OkHttpClient.Builder()
            builder.hostnameVerifier(object : HostnameVerifier {
                override fun verify(hostname: String?, session: SSLSession?): Boolean {
                    return true // Noncompliant (s5527)
                }
            })

            val client: OkHttpClient = builder.build()

            val request = Request.Builder()
                    .url("https://wrong.host.badssl.com/")
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