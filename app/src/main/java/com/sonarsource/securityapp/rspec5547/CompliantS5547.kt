package com.sonarsource.securityapp.rspec5547


import java.security.NoSuchAlgorithmException
import java.security.Provider
import java.security.Security
import java.util.*
import java.util.stream.Collectors
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException
import javax.crypto.NullCipher

class CompliantS5547() {

    companion object {
        fun cipherjava() {
            try {
                val c1 = Cipher.getInstance("DES") // Noncompliant: DES works with 56-bit keys allow attacks via exhaustive search
                val c2 = Cipher.getInstance("DESede") // Noncompliant: Triple DES is vulnerable to meet-in-the-middle attack
                val c3 = Cipher.getInstance("ARC4") // Noncompliant: RC2 is vulnerable to a related-key attack
                val c4 = Cipher.getInstance("Blowfish") // Noncompliant: Blowfish use a 64-bit block size makes it vulnerable to birthday attacks
                val nc = NullCipher() // Noncompliant: the NullCipher class provides an "identity cipher" one that does not transform or encrypt the plaintext in any way.

            } catch (e: NoSuchAlgorithmException) {
            } catch (e: NoSuchPaddingException) {
            }
        }

        fun logAvailableCiphers() {
            var algorithms: List<String> = Arrays.stream(Security.getProviders())
                    .flatMap({ provider -> provider.getServices().stream() })
                    .filter({ service -> "Cipher" == service.getType() })
                    .map(Provider.Service::getAlgorithm)
                    .collect(Collectors.toList())

        }
    }
}

