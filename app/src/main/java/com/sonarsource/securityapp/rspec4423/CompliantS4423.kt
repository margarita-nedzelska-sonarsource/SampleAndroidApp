package com.sonarsource.securityapp.rspec4423


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLParameters
import java.security.NoSuchAlgorithmException
import java.util.*

class CompliantS4423() {

    companion object {
        suspend fun urlConnectionTLSDefault() {

            var content: String = ""

            withContext(Dispatchers.IO) {
                val url = URL("https://tls-v1-1.badssl.com:1011/")
                // by default url.openConnection returns an HttpsURLConnection when connection to https url
                // the tls version depends on the openjdk version
                // we don't handle this case
                val urlConnection: URLConnection = url.openConnection()  // Compliant (s4423)
                val inputStream: InputStream = urlConnection.getInputStream()

                val reader = BufferedReader(inputStream.reader())
                try {
                    content = reader.readText()
                } finally {
                    reader.close()
                }
            }
        }

        fun okHttp() {
            // https://square.github.io/okhttp/3.x/okhttp/okhttp3/TlsVersion.html
            val spec: ConnectionSpec = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2) // Compliant (s4423)
                .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256
                )
                .build()

            val client: OkHttpClient = OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec))
                .build()

            val request = Request.Builder()
                .url("https://tls-v1-1.badssl.com:1011/")
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

        fun logDefaultSSLParameters() {
            val sslParameters: SSLParameters
            try {
                // see also "Default configuration for different Android versions"
                // https://developer.android.com/reference/javax/net/ssl/SSLSocket.html
                sslParameters = SSLContext.getDefault().getDefaultSSLParameters()
            } catch (e: NoSuchAlgorithmException) {
                // ...
            }
        }
    }
}