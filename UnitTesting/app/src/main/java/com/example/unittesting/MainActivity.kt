package com.example.unittesting


fun main() {
    println("Hello World!")
}

/**
 * Sign up a user with user just email address
 */
fun signUpWithEmailOnly(email: String): Boolean {
    return isEmailValid(email)
}

/**
 * Requirements to check:
 * 1) Email should not be blank
 * 2) Email should contain @
 * 3) Email should contain gmail.com domain (Eg. abc@gmail.com)
 * 4) Email should not contain dashes (-) in between letters
 * 5) Email should not start with _ (Underscore)
 */
fun isEmailValid(email: String): Boolean {
    if (email.contains(" "))
        return false
    else if (!email.contains("@"))
        return false
    else if (!email.contains("gmail.com"))
        return false
    else if (email.contains("-"))
        return false
    else if(email.startsWith("_"))
        return false
    return true
}
