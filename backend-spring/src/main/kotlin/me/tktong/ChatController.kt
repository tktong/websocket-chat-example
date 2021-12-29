package me.tktong

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.util.HtmlUtils

@Controller
class ChatController {

    @MessageMapping("/message")
    @SendTo("/topic/chatroom")
    fun sendMessage(message: Message): Message {
        val sanitizedMessageText = HtmlUtils.htmlEscape(message.text)
        return Message(message.username, sanitizedMessageText)
    }
}