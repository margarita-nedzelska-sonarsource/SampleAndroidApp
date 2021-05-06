package com.sonarsource.securityapp.rspec3329


import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CompliantS3329() {

    companion object {
        fun cipherjava() {
            try {

                var random: SecureRandom = SecureRandom()

                var bytesIV: ByteArray = ByteArray(16)
                random.nextBytes(bytesIV);

                val iv = IvParameterSpec(bytesIV)
                val skeySpec = SecretKeySpec("0123456789112345".toByteArray(), "AES")

                val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv) // Noncompliant (s3329)

                val encryptedBytes: ByteArray = cipher.doFinal("foo".toByteArray())
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
        }
    }
}

