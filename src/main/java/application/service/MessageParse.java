package application.service;

import application.model.MessageDto;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class MessageParse {

    public MessageDto parseMessageFromAdmin(Message message){
        String pureMessageText = message.getText();
        int spaceIndex = pureMessageText.indexOf(" ");
        return new MessageDto(Long.valueOf(pureMessageText.substring(0, spaceIndex)), pureMessageText.substring(spaceIndex, pureMessageText.length()),
                message.getFrom().getUserName());
    }

}
