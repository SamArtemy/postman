package application.bot;

import application.bot.util.BotUtil;
import application.handler.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class PostmanBot extends TelegramLongPollingBot {

    @Autowired
    MessageHandler messageHandler;

    private String botUsername;
    private String botToken;

    @Autowired
    private PostmanBot postmanBot;

    public PostmanBot(String botUsername, String botToken, DefaultBotOptions defaultBotOptions){
        super(defaultBotOptions);
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!Long.valueOf(update.getMessage().getFrom().getId().toString()).equals(BotUtil.getBotAdminid())) {
            messageHandler.postGet(this, update);
        } else {
            messageHandler.postSend(this, update);
        }
    }

}
