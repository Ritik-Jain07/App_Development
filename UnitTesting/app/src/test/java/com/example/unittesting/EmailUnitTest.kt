package com.example.unittesting

import org.junit.Assert
import org.junit.Test

class EmailUnitTest {

    @Test
    fun testEmailShouldNotContainSpaces_isCorrect() {
        val emailAddress = "rit ik@gmail.com"
        val emailIsCorrect = signUpWithEmailOnly(emailAddress)
        Assert.assertTrue(emailIsCorrect)
    }

    @Test
    fun testEmailShouldContainCorrectDomain_isCorrect() {
        val emailAddress = "ritik@gmail.com"
        val emailIsCorrect = signUpWithEmailOnly(emailAddress)
        Assert.assertTrue(emailIsCorrect)
    }

    @Test
    fun testEmailShouldNotBeBlank_isCorrect() {
        val emailAddress = ""
        val emailIsCorrect = signUpWithEmailOnly(emailAddress)
        Assert.assertTrue(emailIsCorrect)
    }

    @Test
    fun testEmailFormat_isCorrect() {
        val emailAddress = "ritik@gmail.com"
        val emailIsCorrect = signUpWithEmailOnly(emailAddress)
        Assert.assertTrue(emailIsCorrect)
    }

    @Test
    fun testEmailShouldNotStartWithUnderScore_isCorrect() {
        val emailAddress = "ritik@gmail.com"
        val emailIsCorrect = signUpWithEmailOnly(emailAddress)
        Assert.assertTrue(emailIsCorrect)
    }
}