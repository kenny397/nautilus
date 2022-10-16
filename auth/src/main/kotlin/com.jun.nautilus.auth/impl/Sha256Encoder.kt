package com.jun.nautilus.auth.impl

import com.jun.nautilus.auth.PasswordEncoder
import java.security.MessageDigest

class Sha256Encoder: PasswordEncoder {
    override fun encoding(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(password.encodeToByteArray())
        val digest = md.digest()
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }


}