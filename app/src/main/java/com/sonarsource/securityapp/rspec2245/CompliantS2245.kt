package com.sonarsource.securityapp.rspec2245

import java.security.SecureRandom

class CompliantS2245() {

    companion object {
        fun javasecurerandom() {
            val random = SecureRandom() // Compliant (s2245)
            val bytes = ByteArray(20)
            random.nextBytes(bytes)
        }
    }
}

