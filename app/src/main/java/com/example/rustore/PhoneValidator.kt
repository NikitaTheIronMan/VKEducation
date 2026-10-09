
package com.example.rustore

object PhoneValidator {

    fun normalizeRussianMobilePhone(input: String): String? {
        val trimmed = input.trim()

        if (!trimmed.matches(Regex("""[0-9+\s()\-]+"""))) {
            return null
        }

        val compact = trimmed.replace(Regex("""[\s()\-]"""), "")

        val digits = when {
            compact.startsWith("+7") -> compact.drop(1)
            compact.startsWith("8") -> "7" + compact.drop(1)
            else -> return null
        }

        if (!digits.matches(Regex("""79\d{9}"""))) {
            return null
        }

        return "+$digits"
    }
}
