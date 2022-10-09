package io.project.SpringAlbBot.service;


import com.vdurmont.emoji.EmojiParser;
import io.project.SpringAlbBot.config.BotConfig;
import io.project.SpringAlbBot.model.Build;
import io.project.SpringAlbBot.model.BuildRepository;
import io.project.SpringAlbBot.model.User;
import io.project.SpringAlbBot.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildRepository buildRepository;
    static final String HELP_TEXT = "Команды: \n\n" +
            "/tank - Танки\n\n" +
            "/stoptank - Стоп танки и другие\n\n" +
            "/mdd - Милики\n\n" +
            "/rdd - Дальники\n\n" +
            "/hill - Хилы\n\n" +
            "/druid - Друли\n\n" +
            "/support - Поддержка\n\n";

    final BotConfig config;

    public TelegramBot(BotConfig config) {

        this.config = config;
        List<BotCommand> listOfCommands2 = new ArrayList<>();
        listOfCommands2.add(new BotCommand("/start", "Приветствие"));
        listOfCommands2.add(new BotCommand("/tank", "Танки"));
        listOfCommands2.add(new BotCommand("/stoptank", "Стоп танки и другие"));
        listOfCommands2.add(new BotCommand("/mdd", "Милики"));
        listOfCommands2.add(new BotCommand("/rdd", "Дальники"));
        listOfCommands2.add(new BotCommand("/hill", "Хилы"));
        listOfCommands2.add(new BotCommand("/druid", "Друли"));
        listOfCommands2.add(new BotCommand("/support", "Сапы"));
        listOfCommands2.add(new BotCommand("/help", "info how to use this bot"));


        try {
            this.execute(new SetMyCommands(listOfCommands2, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error setting bot`s command list " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (messageText) {
                case "/start":
                    registerUser(update.getMessage());
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;

                case "/help":
                    sendMessage(chatId, HELP_TEXT);
                    break;

                case "/tank":
                    getTank(chatId, update.getMessage());
                    break;
                case "/stoptank":
                    getStopTank(chatId, update.getMessage());
                    break;
                case "/mdd":
                    getMdd(chatId, update.getMessage());
                    break;
                case "/rdd":
                    getRdd(chatId, update.getMessage());
                    break;
                case "/support":
                    getSupport(chatId, update.getMessage());
                    break;
                case "/hill":
                    getHill(chatId, update.getMessage());
                    break;
                case "/druid":
                    getDruid(chatId, update.getMessage());
                    break;
                default:
                    sendMessage(chatId, "Sorry, command was not recognized!");
            }

        }

    }

    private void registerUser(Message msg) {

        if (userRepository.findById(msg.getChatId()).isEmpty()) {

            var chatId = msg.getChatId();
            var chat = msg.getChat();

            User user = new User();
            user.setChatId(chatId);
            user.setFirstName(chat.getFirstName());
            user.setLastName(chat.getLastName());
            user.setUserName(chat.getUserName());
            user.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);

            log.info("User saved " + user);
        }

    }

    private void startCommandReceived(long chatId, String name) {

        String answer = EmojiParser.parseToUnicode("Hi, " + name + ", nice to meet you" + " :blush:");
        //String answer = "Hi, " + name + ", nice to meet you";
        log.info("Replied to user " + name);

        sendMessage(chatId, answer);

    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    private void getTank(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllTank();

        for (int i = 0; i < answer.size(); i++){
            build = answer.get(i);
            String showBuild = "Оружие: " + build.getWeapon_1() + "\n"
                    + "Голова: " + build.getHead() + "\n"
                    + "Тело: " + build.getBody() + "\n"
                    + "Ноги: " + build.getLegs() + "\n"
                    + "Плащ: " + build.getCloak() + "\n"
                    + "Еда: " + build.getFood() + "\n"
                    + "Банка: " + build.getElixir() + "\n"
                    + "Размер группы: " + build.getSize() + "\n";
            sendMessage(chatID, showBuild);
        }
    }


    private void getStopTank(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllStopTank();

        for (int i = 0; i < answer.size(); i++){
            build = answer.get(i);
            String showBuild = "Оружие: " + build.getWeapon_1() + "\n"
                    + "Голова: " + build.getHead() + "\n"
                    + "Тело: " + build.getBody() + "\n"
                    + "Ноги: " + build.getLegs() + "\n"
                    + "Плащ: " + build.getCloak() + "\n"
                    + "Еда: " + build.getFood() + "\n"
                    + "Банка: " + build.getElixir() + "\n"
                    + "Размер группы: " + build.getSize() + "\n";
            sendMessage(chatID, showBuild);
        }
    }

    private void getMdd(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllMdd();

        for (int i = 0; i < answer.size(); i++){
            build = answer.get(i);
            String showBuild = "Оружие: " + build.getWeapon_1() + "\n"
                    + "Голова: " + build.getHead() + "\n"
                    + "Тело: " + build.getBody() + "\n"
                    + "Ноги: " + build.getLegs() + "\n"
                    + "Плащ: " + build.getCloak() + "\n"
                    + "Еда: " + build.getFood() + "\n"
                    + "Банка: " + build.getElixir() + "\n"
                    + "Размер группы: " + build.getSize() + "\n";
            sendMessage(chatID, showBuild);
        }
    }

    private void getRdd(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllRdd();

        for (int i = 0; i < answer.size(); i++){
            build = answer.get(i);
            String showBuild = "Оружие: " + build.getWeapon_1() + "\n"
                    + "Голова: " + build.getHead() + "\n"
                    + "Тело: " + build.getBody() + "\n"
                    + "Ноги: " + build.getLegs() + "\n"
                    + "Плащ: " + build.getCloak() + "\n"
                    + "Еда: " + build.getFood() + "\n"
                    + "Банка: " + build.getElixir() + "\n"
                    + "Размер группы: " + build.getSize() + "\n";
            sendMessage(chatID, showBuild);
        }
    }

    private void getSupport(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllSupport();

        for (int i = 0; i < answer.size(); i++){
            build = answer.get(i);
            String showBuild = "Оружие: " + build.getWeapon_1() + "\n"
                    + "Голова: " + build.getHead() + "\n"
                    + "Тело: " + build.getBody() + "\n"
                    + "Ноги: " + build.getLegs() + "\n"
                    + "Плащ: " + build.getCloak() + "\n"
                    + "Еда: " + build.getFood() + "\n"
                    + "Банка: " + build.getElixir() + "\n"
                    + "Размер группы: " + build.getSize() + "\n";
            sendMessage(chatID, showBuild);
        }
    }

    private void getDruid(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllDruid();

        for (int i = 0; i < answer.size(); i++){
            build = answer.get(i);
            String showBuild = "Оружие: " + build.getWeapon_1() + "\n"
                    + "Голова: " + build.getHead() + "\n"
                    + "Тело: " + build.getBody() + "\n"
                    + "Ноги: " + build.getLegs() + "\n"
                    + "Плащ: " + build.getCloak() + "\n"
                    + "Еда: " + build.getFood() + "\n"
                    + "Банка: " + build.getElixir() + "\n"
                    + "Размер группы: " + build.getSize() + "\n";
            sendMessage(chatID, showBuild);
        }
    }

    private void getHill(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllHill();

        for (int i = 0; i < answer.size(); i++){
            build = answer.get(i);
            String showBuild = "Оружие: " + build.getWeapon_1() + "\n"
                    + "Голова: " + build.getHead() + "\n"
                    + "Тело: " + build.getBody() + "\n"
                    + "Ноги: " + build.getLegs() + "\n"
                    + "Плащ: " + build.getCloak() + "\n"
                    + "Еда: " + build.getFood() + "\n"
                    + "Банка: " + build.getElixir() + "\n"
                    + "Размер группы: " + build.getSize() + "\n";
            sendMessage(chatID, showBuild);
        }
    }

}
