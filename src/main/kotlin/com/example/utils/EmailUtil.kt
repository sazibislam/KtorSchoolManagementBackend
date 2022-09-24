package com.example.utils

import com.example.plugins.applog.appLog
import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.EmailException
import org.apache.commons.mail.SimpleEmail

fun sendOtpEmail(otp: String, userEmail: String, userName: String) {
    try {

        val simpleEmail = SimpleEmail()
        simpleEmail.hostName = EMAIL_HOST_NAME
        simpleEmail.setSmtpPort(EMAIL_SMTP_PORT)
        simpleEmail.setAuthenticator(DefaultAuthenticator(EMAIL_USERNAME, EMAIL_PASSWORD))
        simpleEmail.isSSLOnConnect = true
        simpleEmail.setFrom(EMAIL_FROM, EMAIL_FROM_NAME)
        simpleEmail.subject = "Verification Code $otp"

        simpleEmail.setContent(otpEmailTemplate(otp), EMAIL_HTML_CONTENT_TYPE)
        simpleEmail.addTo(userEmail, userName)
        simpleEmail.setDebug(true)
        simpleEmail.send()
    } catch (ex: EmailException) {
        appLog(type = "EmailException", message = ex.message ?: USER_INVALID_FAILURE)
    }
}