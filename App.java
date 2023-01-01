package my.uum;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * This class is for run the bot
 */
public class App  {

    /**
     * This method is for run the bot and the bot connect with sqlite
     */

    public static void main(String[] args) {

        SQLite.setConnection();


        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new BotT());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
