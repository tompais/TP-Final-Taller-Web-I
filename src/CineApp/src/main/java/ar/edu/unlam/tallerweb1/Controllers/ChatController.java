package ar.edu.unlam.tallerweb1.Controllers;

import ar.edu.unlam.tallerweb1.Models.Message;
import ar.edu.unlam.tallerweb1.Models.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChatController extends BaseController {
    @MessageMapping("/sendMessage")
    @SendTo("/topic/chat")
    public OutputMessage send(Message message) {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }

    @RequestMapping("/chat")
    public ModelAndView irAChat() {
        return new ModelAndView("ejemplos/chat");
    }
}