package com.sonarsource.securityapp.rspec5532

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import org.apache.commons.net.ftp.FTPClient
import org.apache.commons.net.smtp.SMTPClient
import org.apache.commons.net.telnet.TelnetClient
import java.util.*

class NoncompliantS5532() {

    companion object {
        suspend fun apachecommons() {
            withContext(Dispatchers.IO) {
                try {
                    val telnet = TelnetClient() // Noncompliant (s5532)
                    telnet.connect("127.0.0.1")

                    val ftpClient = FTPClient() // Noncompliant (s5532)
                    ftpClient.connect("127.0.0.1", 21)

                    val smtpClient = SMTPClient() // Noncompliant (s5532)
                    smtpClient.connect("127.0.0.1")
                } catch (e: java.net.ConnectException) {
                    // handler
                }
            }
        }

        fun okHttp() {
            val spec1: ConnectionSpec = ConnectionSpec.Builder(ConnectionSpec.CLEARTEXT) // Noncompliant (s5532)
                .build()

            val client = OkHttpClient.Builder()
                .connectionSpecs(
                    Arrays.asList(
                        ConnectionSpec.MODERN_TLS,
                        ConnectionSpec.CLEARTEXT // Noncompliant (s5532)
                    )
                )
                .build()
        }
    }
}