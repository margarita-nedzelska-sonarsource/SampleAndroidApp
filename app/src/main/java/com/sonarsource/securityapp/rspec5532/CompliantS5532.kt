package com.sonarsource.securityapp.rspec5532

import com.jcraft.jsch.JSch
import com.jcraft.jsch.JSchException
import com.jcraft.jsch.Session
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import java.util.*

class CompliantS5532() {

    companion object {
        fun jsch() {
            try {
                val jsch = JSch()
                val session: Session = jsch.getSession("username", "127.0.0.1", 22) // Compliant (s5532)

                session.setPassword("password")
                session.connect()
            } catch (e: JSchException) {
                // handler
            }
        }

        fun okHttp() {
            val spec1: ConnectionSpec =
                ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS) // Compliant (s5532)
                    .build()

            val client = OkHttpClient.Builder()
                .connectionSpecs(
                    Arrays.asList(
                        ConnectionSpec.MODERN_TLS,
                        ConnectionSpec.COMPATIBLE_TLS // Compliant (s5532)
                    )
                )
                .build()
        }
    }
}

