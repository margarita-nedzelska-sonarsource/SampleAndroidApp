package com.sonarsource.securityapp.rspec2245

import java.util.*

class NoncompliantS2245() {

    companion object {
        fun javarandom() {
            val random = Random() // Noncompliant (s2245)
            val bytes = ByteArray(20)
            random.nextBytes(bytes)
        }
    }
}