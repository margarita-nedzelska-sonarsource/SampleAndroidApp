package com.sonarsource.securityapp.rspec5547

import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException
import javax.crypto.NullCipher

class NoncompliantS5547() {

    companion object {
        fun cipherjava() {
            try {
                val c1 = Cipher.getInstance("AES/GCM/NoPadding") // Compliant (5547)
            } catch (e: NoSuchAlgorithmException) {
            } catch (e: NoSuchPaddingException) {
            }
        }
    }
}