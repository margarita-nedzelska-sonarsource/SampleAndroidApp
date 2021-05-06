package com.sonarsource.securityapp.rspec4790

import java.security.MessageDigest

class NoncompliantS4790() {

    companion object {
        fun cipherjava() {
            val md1: MessageDigest = MessageDigest.getInstance("SHA-1") // Noncompliant (s4790)
            val md2: MessageDigest = MessageDigest.getInstance("SHA-224") // Noncompliant (s4790)
            val md3: MessageDigest = MessageDigest.getInstance("MD5") // Noncompliant (s4790)
        }
    }
}