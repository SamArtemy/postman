package application.bot;

import application.bot.util.BotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

@Service
public class BotMain {

    @Autowired
    private PostmanBot postmanBot;

    public void main(String[] args) {

        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(BotUtil.getBotProxyuser(), BotUtil.getBotProxypassword().toCharArray());
            }
        });

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(postmanBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
