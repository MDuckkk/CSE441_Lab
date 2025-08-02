package com.example.lab10

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.widget.Toast

class mySmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        processSms(context, intent);
    }

    private fun processSms(context: Context, intent: Intent) {
        val extras = intent.extras
        var message = ""
        if (extras != null) {
            val pdus = extras.get("pdus") as? Array<*>
            pdus?.forEach { pdu ->
                val sms = SmsMessage.createFromPdu(pdu as ByteArray)
                val body = sms.messageBody
                val address = sms.originatingAddress
                // Ghép nội dung thông báo
                message += "Có 1 tin nhắn từ $address\n$body vừa gửi đến\n"
            }
            // Hiển thị Toast thông báo
            Toast.makeText(context, message.trim(), Toast.LENGTH_LONG).show()
        }
    }
}