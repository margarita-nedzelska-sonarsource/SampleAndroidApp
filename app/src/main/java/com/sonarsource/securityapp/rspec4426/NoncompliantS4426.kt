package com.sonarsource.securityapp.rspec4426


import java.security.InvalidAlgorithmParameterException
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.ECGenParameterSpec
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException

class NoncompliantS4426() {

    companion object {
        fun cipherjava() {
            try {
                // for RSA, DSA, DH (DiffieHellman), key size should be >= 2048
                val keyPairGen1: KeyPairGenerator = KeyPairGenerator.getInstance("RSA")
                keyPairGen1.initialize(1024) // Noncompliant (s4426)
                val keyPairGen2: KeyPairGenerator = KeyPairGenerator.getInstance("DSA")
                keyPairGen2.initialize(1024, SecureRandom()) // Noncompliant (s4426)
                val keyPairGen3: KeyPairGenerator = KeyPairGenerator.getInstance("DiffieHellman")
                keyPairGen3.initialize(1024) // Noncompliant (s4426)
                val keyPairGen4: KeyPairGenerator = KeyPairGenerator.getInstance("DH")
                keyPairGen4.initialize(1024) // Noncompliant (s4426)

                // for elliptic curves, key size should be >= 224
                val keyPairGen5 = KeyPairGenerator.getInstance("EC")

                val ecSpec1 = ECGenParameterSpec("secp112r1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec1)

                val ecSpec2 = ECGenParameterSpec("secp112r2") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec2)

                val ecSpec3 = ECGenParameterSpec("secp128r1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec3)

                val ecSpec4 = ECGenParameterSpec("secp128r2") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec4)

                val ecSpec5 = ECGenParameterSpec("secp160k1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec5)

                val ecSpec6 = ECGenParameterSpec("secp160r1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec6)

                val ecSpec7 = ECGenParameterSpec("secp160r2") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec7)

                val ecSpec8 = ECGenParameterSpec("secp192k1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec8)

                val ecSpec9 = ECGenParameterSpec("secp192r1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec9)

                val ecSpec10 = ECGenParameterSpec("prime192v2") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec10)

                val ecSpec11 = ECGenParameterSpec("prime192v3") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec11)

                val ecSpec12 = ECGenParameterSpec("sect113r1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec12)

                val ecSpec13 = ECGenParameterSpec("sect113r2") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec13)

                val ecSpec14 = ECGenParameterSpec("sect131r1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec14)

                val ecSpec15 = ECGenParameterSpec("sect131r2") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec15)

                val ecSpec16 = ECGenParameterSpec("sect163k1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec16)

                val ecSpec17 = ECGenParameterSpec("sect163r1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec17)

                val ecSpec18 = ECGenParameterSpec("sect163r2") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec18)

                val ecSpec19 = ECGenParameterSpec("sect193r1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec19)

                val ecSpec20 = ECGenParameterSpec("sect193r2") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec20)

                val ecSpec21 = ECGenParameterSpec("c2tnb191v1") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec21)

                val ecSpec22 = ECGenParameterSpec("c2tnb191v2") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec22)

                val ecSpec23 = ECGenParameterSpec("c2tnb191v3") // Noncompliant (s4426)
                keyPairGen5.initialize(ecSpec23)

                // KeyGenerator: symmetric keys should be >= 128
                val keyGen1: KeyGenerator = KeyGenerator.getInstance("AES")
                keyGen1.init(64) // Noncompliant (s4426)

            } catch (e: NoSuchAlgorithmException) {
            } catch (e: InvalidAlgorithmParameterException) {
                e.printStackTrace()
            }
        }
    }
}