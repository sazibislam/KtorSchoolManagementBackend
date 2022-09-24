package com.example.utils

const val EMAIL_TEMPLATE_TOP =
"<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">\n" +
        "    <div style=\"margin:30px auto;width:70%;padding:20px 0\">\n" +
        "        <div style=\"border-bottom:1px solid #eee\">\n" +
        "            <a href=\"\" style=\"font-size:1.8em;color: #00466a;text-decoration:none;font-weight:600\">Ai POS App</a>\n" +
        "        </div>\n" +
        "        <p style=\"font-size:1.2em\">Hello,</p>\n" +
        "        <p>Thank you for your time. Use the following OTP to complete your procedures. OTP is valid for 5 minutes</p>\n" +
        "        <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">"

const val EMAIL_TEMPLATE_BOTTOM = "</h2>\n" +
        "        <p style=\"font-size:1.0em;\">Regards,<br />Tech Nest Team</p>\n" +
        "        <hr style=\"border:none;border-top:1px solid #eee\" />\n" +
        "        <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.9em;line-height:1;font-weight:300\">\n" +
        "            <p>Tech Nest</p>\n" +
        "            <p>www.technest.com.bd</p>\n" +
        "            <p>G-Block, Road-14, Bashundhara, Dhaka.</p>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</div>"

fun otpEmailTemplate(otp: String): String = EMAIL_TEMPLATE_TOP + otp + EMAIL_TEMPLATE_BOTTOM

