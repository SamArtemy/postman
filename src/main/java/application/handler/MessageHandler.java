package application.handler;

import application.bot.PostmanBot;
import application.bot.util.BotUtil;
import application.model.User;
import application.repository.UserRepository;
import application.model.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import application.service.MessageParse;

@Service
@ComponentScan
public class MessageHandler {

    @Autowired
    private MessageParse messageParse;
    @Autowired
    private UserRepository userRepository;

    public void postGet(PostmanBot bot, Update update){
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage()
                .setChatId(BotUtil.getBotAdminid()).setText(message.getFrom().getId() + " @" + message.getFrom().getUserName() + " \n" + message.getText());
        userRepository.save(new User("@" + message.getFrom().getUserName(), message.getFrom().getId().toString()));
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void postSend(PostmanBot bot, Update update){
        MessageDto messageDto = messageParse.parseMessageFromAdmin(update.getMessage());
        SendMessage sendMessage = new SendMessage()
                .setChatId(messageDto.getUserId()).setText(messageDto.getText());
        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
