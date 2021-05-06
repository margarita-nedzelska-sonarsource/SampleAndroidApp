package com.sonarsource.securityapp.rspec4790


import java.security.MessageDigest
import java.security.Provider
import java.security.Security
import java.util.*
import java.util.stream.Collectors

class CompliantS4790() {

    companion object {
        fun supportedalgos() {
            var algorithms: List<String> = Arrays.stream(Security.getProviders())
                    .flatMap({ provider -> provider.getServices().stream() })
                    .filter({ service -> "MessageDigest" == service.getType() })
                    .map(Provider.Service::getAlgorithm)
                    .collect(Collectors.toList())

        }

        fun cipherjava() {
            val md1: MessageDigest = MessageDigest.getInstance("SHA-256") // Compliant (s4790)
            val md2: MessageDigest = MessageDigest.getInstance("SHA-384") // Compliant (s4790)
            val md3: MessageDigest = MessageDigest.getInstance("SHA-512") // Compliant (s4790)
        }
    }
}

