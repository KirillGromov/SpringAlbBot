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
                case "ТАНКИ":
                case "ТАНК":
                case "Танки":
                case "танки":
                case "танк":
                case "Танк":
                case "/TANK":
                case "/tank":
                    getTank(chatId, update.getMessage());
                    break;
                case "стоптанк":
                case "стоп-танк":
                case "Стоп-танк":
                case "Стоптанк":
                case "/stoptank":
                    getStopTank(chatId, update.getMessage());
                    break;
                case "милики":
                case "Милики":
                case "Милик":
                case "милик":
                case "Мдд":
                case "МДД":
                case "мдд":
                case "/mdd":
                    getMdd(chatId, update.getMessage());
                    break;
                case "Дальники":
                case "Рдд":
                case "РДД":
                case "рдд":
                case "/rdd":
                    getRdd(chatId, update.getMessage());
                    break;
                case "/support":
                    getSupport(chatId, update.getMessage());
                    break;
                case "холики":
                case "Холики":
                case "холик":
                case "Холик":
                case "хилы":
                case "Хилы":
                case "хил":
                case "Хил":
                case "/hill":
                    getHill(chatId, update.getMessage());
                    break;
                case "друль":
                case "Друль":
                case "друиды":
                case "Друиды":
                case "друид":
                case "Друид":
                case "/druid":
                    getDruid(chatId, update.getMessage());
                    break;
                case "Алебарда":
                    getAleba(chatId, update.getMessage());
                    break;
                case "падший":
                case "Падший":
                case "Падших":
                case "падших":
                case "посох падших":
                case "Посох падших":
                    getPadsh(chatId, update.getMessage());
                    break;
                case "благодатный":
                case "Благодатный":
                case "благодатный посох":
                case "Благодатный посох":
                    getBlago(chatId, update.getMessage());
                    break;
                case "Большой священный посох":
                case "большой священный посох":
                case "Большой священный":
                case "большой священный":
                case "Большойсвященный":
                case "большойсвященный":
                    getBigHoly(chatId, update.getMessage());
                    break;
                case "Дикий посох":
                case "дикий посох":
                case "Дикийпосох":
                case "дикийпосох":
                    getWild(chatId, update.getMessage());
                    break;
                case "посох порчи":
                case "Посох порчи":
                case "посохпорчи":
                case "Посохпорчи":
                    getDamage(chatId, update.getMessage());
                    break;
                case "загадочный посох":
                case "Загадочный посох":
                case "загадочныйпосох":
                case "Загадочныйпосох":
                    getMysterious(chatId, update.getMessage());
                    break;
                case "средоточие злобы":
                case "Средоточие злобы":
                case "средоточиезлобы":
                case "Средоточиезлобы":
                    getZloba(chatId, update.getMessage());
                    break;
                case "мистический посох":
                case "Мистический посох":
                case "мистическийпосох":
                case "Мистическийпосох":
                    getMystical(chatId, update.getMessage());
                    break;
                case "песнь заката":
                case "Песнь заката":
                case "песньзаката":
                case "Песньзаката":
                    getSong(chatId, update.getMessage());
                    break;
                case "гибельный посох":
                case "Гибельный посох":
                case "гибельныйпосох":
                case "Гибельныйпосох":
                    getDisastrous(chatId, update.getMessage());
                    break;
                case "посох обреченности":
                case "Посох обреченности":
                case "посохобреченности":
                case "Посохобреченности":
                    getDoom(chatId, update.getMessage());
                    break;
                case "заледенелый посох":
                case "Заледенелый посох":
                case "заледенелыйпосох":
                case "Заледенелыйпосох":
                    getIcy(chatId, update.getMessage());
                    break;
                case "хранители клятвы":
                case "Хранители клятвы":
                case "хранителиклятвы":
                case "Хранителиклятвы":
                    getOathKeeper(chatId, update.getMessage());
                    break;
                case "разрушитель миров":
                case "Разрушитель миров":
                case "рзрушительмиров":
                case "Разрушительмиров":
                    getDestroyer(chatId, update.getMessage());
                    break;
                case "булава Камлана":
                case "Булава Камлана":
                case "булаваКамлана":
                case "БулаваКамлана":
                    getKamlan(chatId, update.getMessage());
                    break;
                case "коса душ":
                case "Коса душ":
                case "косадуш":
                case "Косадуш":
                    getScytheSouls(chatId, update.getMessage());
                    break;
                case "булава":
                case "Булава":
                    getMace(chatId, update.getMessage());
                    break;
                case "длань правосудия":
                case "Длань правосудия":
                case "дланьправосудия":
                case "Дланьправосудия":
                    getJustice(chatId, update.getMessage());
                    break;
                case "тяжёлая булава":
                case "Тяжёлая булава":
                case "тяжёлаябулава":
                case "Тяжёлаябулава":
                    getHeavyMace(chatId, update.getMessage());
                    break;
                case "хранитель рощи":
                case "Хранитель рощи":
                case "хранительрощи":
                case "Хранительрощи":
                    getKeeperOfGrove(chatId, update.getMessage());
                    break;
                case "искатель Грааля":
                case "Искатель Грааля":
                case "искательГрааля":
                case "ИскательГрааля":
                case "искатель грааля":
                case "Искатель грааля":
                case "искательграаля":
                case "Искательграаля":
                    getGraal(chatId, update.getMessage());
                    break;
                case "песнь рассвета":
                case "Песнь рассвета":
                case "песньрассвета":
                case "Песньрассвета":
                    getDawn(chatId, update.getMessage());
                    break;
                case "бадон":
                case "Бадон":
                    getBadon(chatId, update.getMessage());
                    break;
                case "туманный пронзатель":
                case "Туманный пронзатель":
                case "туманныйпронзатель":
                case "Туманныйпронзатель":
                    getImpaler(chatId, update.getMessage());
                    break;
                case "арбалет":
                case "Арбалет":
                    getCrossbow(chatId, update.getMessage());
                    break;
                case "плачущий арбалет":
                case "Плачущий арбалет":
                case "плачущийарбалет":
                case "Плачущийарбалет":
                    getSadCrossbow(chatId, update.getMessage());
                    break;
                case "осадный арбалет":
                case "Осадный арбалет":
                case "осадныйарбалет":
                case "Осадныйарбалет":
                    getSiegeCrossbow(chatId, update.getMessage());
                    break;
                case "творец энергии":
                case "Творец энергии":
                case "творецэнергии":
                case "Творецэнергии":
                    getEnergyCreator(chatId, update.getMessage());
                    break;
                case "посох лесного пожара":
                case "Посох лесного пожара":
                case "посохлесногопожара":
                case "Посохлесногопожара":
                case "лесного пожара":
                case "Лесного пожара":
                case "лесногопожара":
                case "Лесногопожара":
                    getWildfireStaff(chatId, update.getMessage());
                    break;
                case "посох серы":
                case "Посох серы":
                case "посохсеры":
                case "Посохсеры":
                    getStaffOfSulfur(chatId, update.getMessage());
                    break;
                case "призма вечного холода":
                case "Призма вечного холода":
                case "призмавечногохолода":
                case "Призмавечногохолода":
                case "призма":
                case "Призма":
                    getPrismCold(chatId, update.getMessage());
                    break;
                case "демонический посох":
                case "Демонический посох":
                case "демоническийпосох":
                case "Демоническийпосох":
                    getDemonStaff(chatId, update.getMessage());
                    break;
                case "большой проклятый посох":
                case "Большой проклятый посох":
                case "большойпроклятый посох":
                case "Большойпроклятыйпосох":
                case "большой проклятый":
                case "Большой проклятый":
                case "большойпроклятый":
                case "Большойпроклятый":
                    getGreatCursedStaff(chatId, update.getMessage());
                    break;
                case "проклятый череп":
                case "Проклятый череп":
                case "проклятыйчереп":
                case "Проклятыйчереп":
                    getCursedSkull(chatId, update.getMessage());
                    break;
                case "большой топор":
                case "Большой топор":
                case "большойтопор":
                case "Большойтопор":
                    getBigAx(chatId, update.getMessage());
                    break;
                case "адская коса":
                case "Адская коса":
                case "адскаякоса":
                case "Адскаякоса":
                    getHellishScythe(chatId, update.getMessage());
                    break;
                case "медвежьи лапы":
                case "Медвежьи лапы":
                case "медвежьилапы":
                case "Медвежьилапы":
                    getBearPaws(chatId, update.getMessage());
                    break;
                case "королевский меч":
                case "Королевский меч":
                case "королевскиймеч":
                case "Королевскиймеч":
                    getRoyalSword(chatId, update.getMessage());
                    break;
                case "меч резни":
                case "Меч резни":
                case "мечрезни":
                case "Мечрезни":
                    getSwordOfCarnage(chatId, update.getMessage());
                    break;
                case "галатины":
                case "Галатины":
                    getGalatians(chatId, update.getMessage());
                    break;
                case "гаретворец":
                case "Царетворец":
                    getKingmaker(chatId, update.getMessage());
                    break;
                case "охотник за душами":
                case "Охотник за душами":
                case "охотникзадушами":
                case "Охотникзадушами":
                    getSoulsHunter(chatId, update.getMessage());
                    break;
                case "копьё рассвета":
                case "Копьё рассвета":
                case "копьёрассвета":
                case "Копьёрассвета":
                    getSpearOfDawn(chatId, update.getMessage());
                    break;
                case "кровопускатель":
                case "Кровопускатель":
                case "крововпуск":
                case "Крововпуск":
                    getBloodletter(chatId, update.getMessage());
                    break;
                case "клык демона":
                case "Клык демона":
                case "клыкдемона":
                case "Клыкдемона":
                    getDemonFang(chatId, update.getMessage());
                    break;
                case "укротители ярости":
                case "Укротители ярости":
                case "укротителиярости":
                case "Укротителиярости":
                    getRageTamers(chatId, update.getMessage());
                    break;
                case "шипастые перчатки":
                case "Шипастые перчатки":
                case "шипастыеперчатки":
                case "Шипастыеперчатки":
                    getSpikedGloves(chatId, update.getMessage());
                    break;
                case "медвежьи перчатки":
                case "Медвежьи перчатки":
                case "медвежьиперчатки":
                case "Медвежьиперчатки":
                    getBearGloves(chatId, update.getMessage());
                    break;
                case "вороний цестус":
                case "Вороний цестус":
                case "воронийцестус":
                case "Воронийцестус":
                case "цестус":
                case "Цестус":
                    getRavenCestus(chatId, update.getMessage());
                    break;
                case "кулаки авалона":
                case "Кулаки авалона":
                case "кулакиавалона":
                case "Кулакиавалона":
                case "кулаки":
                case "Кулаки":
                    getFistsOfAvalon(chatId, update.getMessage());
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

    private void getAleba(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllAleba();

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

    private void getPadsh(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllPadhsh();

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

    private void getBlago(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllBolago();

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

    private void getBigHoly(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllBigHoly();

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

    private void getWild(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllWild();

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

    private void getDamage(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllDamage();

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

    private void getMysterious(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllMysterious();

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

    private void getZloba(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllZloba();

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

    private void getMystical(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllMystical();

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

    private void getSong(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllSong();

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

    private void getDisastrous(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllDisastrous();

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

    private void getDoom(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllDoom();

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

    private void getIcy(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllIcy();

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

    private void getOathKeeper(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllOathKeeper();

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

    private void getDestroyer(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllDestroyer();

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

    private void getKamlan(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllKamlan();

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

    private void getScytheSouls(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllScytheSouls();

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

    private void getMace(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllMace();

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

    private void getJustice(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllJustice();

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

    private void getHeavyMace(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllHeavyMace();

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

    private void getKeeperOfGrove(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllKeeperOfGrove();

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

    private void getGraal(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllGraal();

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

    private void getDawn(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllDawn();

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

    private void getBadon(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllBadon();

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

    private void getImpaler(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllImpaler();

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

    private void getCrossbow(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllCrossbow();

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

    private void getSadCrossbow(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllSadCrossbow();

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

    private void getSiegeCrossbow(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllSiegeCrossbow();

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

    private void getEnergyCreator(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllEnergyCreator();

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

    private void getWildfireStaff(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllWildfireStaff();

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

    private void getStaffOfSulfur(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllStaffOfSulfur();

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

    private void getPrismCold(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllPrismCold();

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

    private void getDemonStaff(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllDemonStaff();

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

    private void getGreatCursedStaff(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllGreatCursedStaff();

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

    private void getCursedSkull(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllCursedSkull();

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

    private void getBigAx(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllBigAx();

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

    private void getHellishScythe(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllHellishScythe();

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

    private void getBearPaws(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllBearPaws();

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

    private void getRoyalSword(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllRoyalSword();

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

    private void getSwordOfCarnage(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllSwordOfCarnage();

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

    private void getGalatians(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllGalatians();

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

    private void getKingmaker(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllKingmaker();

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

    private void getSoulsHunter(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllSoulsHunter();

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

    private void getSpearOfDawn(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllSpearOfDawn();

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

    private void getBloodletter(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllBloodletter();

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

    private void getDemonFang(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllDemonFang();

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

    private void getRageTamers(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllRageTamers();

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

    private void getSpikedGloves(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllSpikedGloves();

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

    private void getBearGloves(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllBearGloves();

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

    private void getRavenCestus(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllRavenCestus();

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

    private void getFistsOfAvalon(long chatID, Message msg){

        var chat = msg.getChat();
        log.info("_______________________________");
        Build build = new Build();

        List<Build> answer = buildRepository.findAllFistsOfAvalon();

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
