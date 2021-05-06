package com.sonarsource.securityapp.rspec5542

import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException

class CompliantS5542() {

    companion object {
        fun cipherjava() {
            try {
                val c1 = Cipher.getInstance("AES") // Compliant (s5542)
                val c2 = Cipher.getInstance("AES/CBC/PKCS5Padding") // Compliant (s5542)
                val c3 = Cipher.getInstance("AES/CBC/PKCS7Padding") // Compliant (s5542)
                val c4 = Cipher.getInstance("AES/GCM/NoPadding") // Compliant (s5542)
                val c5 = Cipher.getInstance("RSA/None/OAEPWithSHA-1AndMGF1Padding") // Compliant (s5542)
                val c6 = Cipher.getInstance("RSA/None/OAEPWITHSHA-256ANDMGF1PADDING") // Compliant (s5542)
            } catch (e: NoSuchAlgorithmException) {
            } catch (e: NoSuchPaddingException) {
            }
        }
    }
}

