package com.example.utils

import java.util.regex.Pattern

const val EMAIL_PASSWORD = "fmtwlajllakekoat"
const val EMAIL_USERNAME = "sazibbtrac@gmail.com"
const val EMAIL_SMTP_PORT = 465
const val EMAIL_HOST_NAME = "smtp.googlemail.com"
const val EMAIL_FROM = "noreply@technest.com.bd"
const val TECH_NEST = "Tech Nest"
const val EMAIL_HTML_CONTENT_TYPE = "text/html; charset=utf-8"
internal val EMAIL_ADDRESS_PATTERN = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

const val PACKAGE_NAME = "package"
const val PACKAGE_ANDROID = "com.tn.aipos"
