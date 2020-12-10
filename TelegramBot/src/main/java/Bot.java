import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;

import static org.telegram.telegrambots.logging.BotLogger.log;

public class Bot extends TelegramLongPollingBot {



    @Override
    public void onUpdateReceived(Update update) {
//    String message = update.getMessage().getText();

        Message msg = update.getMessage(); // Это нам понадобится
        String txt = msg.getText();
        if (txt.equals("/start")) {
            sendMsg(update.getMessage().getChatId().toString(), "Добро пожаловать!\n" +
                                                                "Чтобы узнать курс, выберите валюту.");
        } else if (txt.equals("/name")) {
            sendMsg(update.getMessage().getChatId().toString(), getBotUsername().toString());
        } else if (txt.equals("USD")) {
            String text = new ParserJson().getAmount(txt);
            sendMsg(update.getMessage().getChatId().toString(), "1 доллар " + new ParserJson().getAmount(txt));
        } else if (txt.equals("EUR")) {
            String text = new ParserJson().getAmount(txt);
            sendMsg(update.getMessage().getChatId().toString(), "1 евро " + new ParserJson().getAmount(txt));
        } else if (txt.equals("RUB")) {
            String text = new ParserJson().getAmount(txt);
            sendMsg(update.getMessage().getChatId().toString(), "1 рубль " + new ParserJson().getAmount(txt));
        }

    }

    public synchronized void sendMsg(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        setButtons(sendMessage);
        try {
            execute(sendMessage);  // sendMessage(sendMessage) заменил на то что есть
        } catch (TelegramApiException e) {
            log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return "MyFirst";  //
    }

    @Override
    public String getBotToken() {
        return "1427646854:AAFYd6bninkDt_nIYLmHZo0ab8Vw4M0iC1w";
    }

    public synchronized void setButtons(SendMessage sendMessage) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("USD"));
        keyboardFirstRow.add(new KeyboardButton("RUB"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("EUR"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    // отключаем вывод предупреждения об чем?? ...нелелгальный рефлективный доступ...
    public static void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }
}
