package com.sonarsource.securityapp.rspec4426


import java.security.*
import java.security.spec.ECGenParameterSpec
import javax.crypto.KeyGenerator

class CompliantS4426() {

    companion object {
        fun cipherjava() {
            try {
                // for RSA, DSA, DH (DiffieHellman), key size should be >= 2048
                val keyPairGen1: KeyPairGenerator = KeyPairGenerator.getInstance("RSA")
                keyPairGen1.initialize(2048) // Compliant (s4426)
                val keyPairGen2: KeyPairGenerator = KeyPairGenerator.getInstance("DSA")
                keyPairGen2.initialize(2048, SecureRandom()) // Compliant (s4426)
                val keyPairGen3: KeyPairGenerator = KeyPairGenerator.getInstance("DiffieHellman")
                keyPairGen3.initialize(2048) // Compliant (s4426)
                val keyPairGen4: KeyPairGenerator = KeyPairGenerator.getInstance("DH")
                keyPairGen4.initialize(2048) // Compliant (s4426)

                // for elliptic curves, key size should be >= 224
                val keyPairGen5 = KeyPairGenerator.getInstance("EC")

                val ecSpec1 = ECGenParameterSpec("secp256r1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec1)

                val ecSpec2 = ECGenParameterSpec("secp224r1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec2)

                val ecSpec3 = ECGenParameterSpec("secp256k1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec3)

                val ecSpec4 = ECGenParameterSpec("secp256r1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec4)

                val ecSpec5 = ECGenParameterSpec("secp384r1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec5)

                val ecSpec6 = ECGenParameterSpec("secp521r1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec6)

                val ecSpec7 = ECGenParameterSpec("prime239v1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec7)

                val ecSpec8 = ECGenParameterSpec("prime239v2") // compliant (s4426)
                keyPairGen5.initialize(ecSpec8)

                val ecSpec9 = ECGenParameterSpec("prime239v3") // compliant (s4426)
                keyPairGen5.initialize(ecSpec9)

                val ecSpec10 = ECGenParameterSpec("sect233k1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec10)

                val ecSpec11 = ECGenParameterSpec("sect233r1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec11)

                val ecSpec12 = ECGenParameterSpec("sect239k1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec12)

                val ecSpec13 = ECGenParameterSpec("sect283k1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec13)

                val ecSpec14 = ECGenParameterSpec("sect283r1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec14)

                val ecSpec15 = ECGenParameterSpec("sect409k1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec15)

                val ecSpec16 = ECGenParameterSpec("sect409r1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec16)

                val ecSpec17 = ECGenParameterSpec("sect571k1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec17)

                val ecSpec18 = ECGenParameterSpec("sect571r1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec18)

                val ecSpec19 = ECGenParameterSpec("c2tnb239v1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec19)

                val ecSpec20 = ECGenParameterSpec("c2tnb239v2") // compliant (s4426)
                keyPairGen5.initialize(ecSpec20)

                val ecSpec21 = ECGenParameterSpec("c2tnb239v3") // compliant (s4426)
                keyPairGen5.initialize(ecSpec21)

                val ecSpec22 = ECGenParameterSpec("c2tnb359v1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec22)

                val ecSpec23 = ECGenParameterSpec("c2tnb431r1") // compliant (s4426)
                keyPairGen5.initialize(ecSpec23)

                // KeyGenerator: symmetric keys should be >= 128
                val keyGen1: KeyGenerator = KeyGenerator.getInstance("AES")
                keyGen1.init(128) // Compliant (s4426)

            } catch (e: NoSuchAlgorithmException) {
            } catch (e: InvalidAlgorithmParameterException) {
                e.printStackTrace()
            }
        }
    }
}

