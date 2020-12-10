import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

public class BotMainClass {
    public static void main(String[] args) throws IOException {

//        ParserJson pjs = new ParserJson();
//        List<Currency> list = pjs.read();
//        System.out.println(pjs.getAmount("USD"));
//         for (Currency a : list) {
//             System.out.println(a);
//         }




        Bot.disableWarning();
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot()); // Bot.getBot() change to new Bot()

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }
}
