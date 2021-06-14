package com.sonarsource.securityapp.rspec4830


import okhttp3.*
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*
import javax.security.cert.CertificateException


class NoncompliantS4830() {

    companion object {
        fun okHttp() {
            val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(chain: Array<X509Certificate?>?,
                                                        authType: String?) {  // Noncompliant (s4830)
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(chain: Array<X509Certificate?>?,
                                                        authType: String?) { // Noncompliant (s4830)
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
            )

            val sslContext: SSLContext = SSLContext.getInstance("TLS")
            sslContext.init(null, trustAllCerts, SecureRandom())

            val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, (trustAllCerts[0] as X509TrustManager)!!)

            builder.hostnameVerifier(object : HostnameVerifier {
                override fun verify(hostname: String?, session: SSLSession?): Boolean {
                    return true
                }
            })

            val client: OkHttpClient = builder.build()

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
