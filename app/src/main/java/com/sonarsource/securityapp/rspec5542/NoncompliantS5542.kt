package com.sonarsource.securityapp.rspec5542

import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException

class NoncompliantS5542() {

    companion object {
        fun cipherjava() {
            try {
                val c0 = Cipher.getInstance("AES") // Noncompliant: by default ECB mode is chosen
                val c1 = Cipher.getInstance("AES/ECB/NoPadding") // Noncompliant: ECB doesn't provide serious message confidentiality
                val c3 = Cipher.getInstance("Blowfish/ECB/PKCS5Padding") // Noncompliant: ECB doesn't provide serious message confidentiality
                val c4 = Cipher.getInstance("DES/ECB/PKCS5Padding") // Noncompliant: ECB doesn't provide serious message confidentiality
                val c14 = Cipher.getInstance("RSA/NONE/NoPadding") // Noncompliant: RSA without OAEP padding scheme is not recommanded
            } catch (e: NoSuchAlgorithmException) {
            } catch (e: NoSuchPaddingException) {
            }
        }
    }
}