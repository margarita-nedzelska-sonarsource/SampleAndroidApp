package com.sonarsource.securityapp.rspec3329


import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class NoncompliantS3329() {

    companion object {
        fun cipherjava() {
            try {
                val bytesIV = "7cVgr5cbdCZVw5WY".toByteArray(charset("UTF-8"))

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