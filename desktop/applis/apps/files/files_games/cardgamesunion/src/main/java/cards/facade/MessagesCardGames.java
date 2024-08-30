package cards.facade;

import code.scripts.messages.cards.*;
import code.sml.util.*;

public final class MessagesCardGames {
    public static final String CARDS = "cards";
    public static final String EDITOR_CARDS = "editor";
    public static final String DIALOG_BELOTE = "dialog_belote";
    public static final String DIALOG_PRESIDENT = "dialog_president";
    public static final String DIALOG_TAROT = "dialog_tarot";
    public static final String DIALOG_NICKNAME = "dialog_nickname";
    public static final String DIALOG_SOFT = "dialog_soft";
    public static final String DIALOG_DISPLAY = "dialog_display";
    public static final String DIALOG_HELP = "dialog_help";
    public static final String MAIN_GAME = "main_game";
    public static final String SIMU_GAME = "simu_game";
    public static final String MENUS = "menus";
    public static final String NETWORK = "network";
    public static final String GAMES_NAMES = "games_names";
    public static final String NICK_NAMES = "nick_names";
    public static final String COMMON_BELOTE = "com_belote";
    public static final String COMMON_PRESIDENT = "com_president";
    public static final String COMMON_TAROT = "com_tarot";
    public static final String GAME_BELOTE = "game_belote";
    public static final String GAME_PRESIDENT = "game_president";
    public static final String GAME_TAROT = "game_tarot";
    public static final String CHOICE_TAROT = "com_ch_tarot";
    public static final String COMMON_CARDS = "common_cards";
    public static final String COMMON_FILE = "common_file";
    public static final String COMMON_MIX = "common_mix";
    //    public static final String USER_DEF = "_";
    //    public static final String NICKNAME_DEF = "_{0}";
    public static final String NICKNAME = "0";
    public static final String USER = "1";
    public static final String TEMP_FOLDER = Translations.TEMP_FOLDER;
    public static final String PARAMS = "1";
    public static final String PLAYERS = "2";
    public static final String PORT_INI = "3";
    public static final String COORDS="4";
    public static final String DECK_FOLDER="5";
    public static final String DECK_FILE="6";
    public static final String DISPLAY_BELOTE="7";
    public static final String DISPLAY_PRESIDENT="8";
    public static final String DISPLAY_TAROT="9";
    public static final String RULES_BELOTE="10";
    public static final String RULES_PRESIDENT="11";
    public static final String RULES_TAROT="12";
    private MessagesCardGames() {
    }

    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(CARDS, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(CARDS);
    }

    public static TranslationsAppli initAppliFilesTr(Translations _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getFiles().addEntry(CARDS, a_);
        return a_;
    }

    public static TranslationsAppli getAppliFilesTr(Translations _lgs) {
        return _lgs.getFiles().getVal(CARDS);
    }

    public static void enTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(EDITOR_CARDS, MessagesEditorCards.en());
        _lgs.getMapping().addEntry(DIALOG_BELOTE, MessagesDialogBelote.en());
        _lgs.getMapping().addEntry(DIALOG_PRESIDENT, MessagesDialogPresident.en());
        _lgs.getMapping().addEntry(DIALOG_TAROT, MessagesDialogTarot.en());
        _lgs.getMapping().addEntry(DIALOG_NICKNAME, MessagesGuiCards.enNickname());
        _lgs.getMapping().addEntry(DIALOG_SOFT,MessagesGuiCards.enSoft());
        _lgs.getMapping().addEntry(DIALOG_DISPLAY,MessagesGuiCards.enDisplay());
        _lgs.getMapping().addEntry(DIALOG_HELP,MessagesGuiCards.enHelp());
        _lgs.getMapping().addEntry(MAIN_GAME,MessagesGuiCards.enGame());
        _lgs.getMapping().addEntry(SIMU_GAME,MessagesGuiCards.enSimu());
        _lgs.getMapping().addEntry(MENUS,MessagesGuiCards.enMenu());
        _lgs.getMapping().addEntry(NETWORK,MessagesGuiCards.enMulti());
        appendCommonCards(_lgs, MessagesCommonCards.en());
        appendCommonFile(_lgs, MessagesCommonFile.en());
        appendCommonMix(_lgs, MessagesCommonMix.en());
        appendCommonBelote(_lgs, MessagesBelote.en());
        appendGameBelote(_lgs, MessagesBelote.enGame());
        appendCommonPresident(_lgs, MessagesPresident.en());
        appendGamePresident(_lgs, MessagesPresident.enGame());
        appendCommonTarot(_lgs, MessagesTarot.en());
        appendGameTarot(_lgs, MessagesTarot.enGame());
        appendCommonChTarot(_lgs, MessagesChoiceTarot.en());
        appendGamesNames(_lgs, MessagesGamesGames.en());
        appendNickNames(_lgs, en());
    }

    public static void frTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(EDITOR_CARDS,MessagesEditorCards.fr());
        _lgs.getMapping().addEntry(DIALOG_BELOTE,MessagesDialogBelote.fr());
        _lgs.getMapping().addEntry(DIALOG_PRESIDENT,MessagesDialogPresident.fr());
        _lgs.getMapping().addEntry(DIALOG_TAROT,MessagesDialogTarot.fr());
        _lgs.getMapping().addEntry(DIALOG_NICKNAME,MessagesGuiCards.frNickname());
        _lgs.getMapping().addEntry(DIALOG_SOFT,MessagesGuiCards.frSoft());
        _lgs.getMapping().addEntry(DIALOG_DISPLAY,MessagesGuiCards.frDisplay());
        _lgs.getMapping().addEntry(DIALOG_HELP,MessagesGuiCards.frHelp());
        _lgs.getMapping().addEntry(MAIN_GAME,MessagesGuiCards.frGame());
        _lgs.getMapping().addEntry(SIMU_GAME,MessagesGuiCards.frSimu());
        _lgs.getMapping().addEntry(MENUS,MessagesGuiCards.frMenu());
        _lgs.getMapping().addEntry(NETWORK,MessagesGuiCards.frMulti());
        appendCommonCards(_lgs, MessagesCommonCards.fr());
        appendCommonFile(_lgs, MessagesCommonFile.fr());
        appendCommonMix(_lgs, MessagesCommonMix.fr());
        appendCommonBelote(_lgs, MessagesBelote.fr());
        appendGameBelote(_lgs, MessagesBelote.frGame());
        appendCommonPresident(_lgs, MessagesPresident.fr());
        appendGamePresident(_lgs, MessagesPresident.frGame());
        appendCommonTarot(_lgs, MessagesTarot.fr());
        appendGameTarot(_lgs, MessagesTarot.frGame());
        appendCommonChTarot(_lgs, MessagesChoiceTarot.fr());
        appendGamesNames(_lgs, MessagesGamesGames.fr());
        appendNickNames(_lgs, fr());
    }

    public static void sys(TranslationsAppli _lgs) {
        _lgs.sys(mes());
    }
    public static TranslationsFile mes(){
        TranslationsFile t_ = new TranslationsFile();
        t_.add(TEMP_FOLDER,CARDS);
        t_.add(PARAMS,"parametres.xml");
        t_.add(PLAYERS,"joueurs.xml");
        t_.add(PORT_INI,"cards_port.xml");
        t_.add(COORDS,"cards.coords");
        t_.add(DECK_FOLDER,"Paquets");
        t_.add(DECK_FILE,"Paquet.txt");
        t_.add(DISPLAY_BELOTE,"dbelote.xml");
        t_.add(DISPLAY_PRESIDENT,"dpresident.xml");
        t_.add(DISPLAY_TAROT,"dtarot.xml");
        t_.add(RULES_BELOTE,"belote.xml");
        t_.add(RULES_PRESIDENT,"president.xml");
        t_.add(RULES_TAROT,"tarot.xml");
        return t_;
    }
    public static void appendCommonCards(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(COMMON_CARDS, _f);
    }

    public static void appendCommonFile(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(COMMON_FILE, _f);
    }

    public static void appendCommonMix(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(COMMON_MIX, _f);
    }

    public static void appendCommonBelote(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(COMMON_BELOTE, _f);
    }

    public static void appendGameBelote(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(GAME_BELOTE, _f);
    }

    public static void appendCommonPresident(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(COMMON_PRESIDENT, _f);
    }

    public static void appendGamePresident(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(GAME_PRESIDENT, _f);
    }

    public static void appendCommonTarot(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(COMMON_TAROT, _f);
    }

    public static void appendGameTarot(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(GAME_TAROT, _f);
    }

    public static void appendCommonChTarot(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(CHOICE_TAROT, _f);
    }

    public static void appendGamesNames(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(GAMES_NAMES, _f);
    }

    public static void appendNickNames(TranslationsAppli _lgs, TranslationsFile _f) {
        _lgs.getMapping().addEntry(NICK_NAMES, _f);
    }

    public static TranslationsFile getEditorTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(EDITOR_CARDS);
    }

    public static TranslationsFile getDialogBeloteTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(DIALOG_BELOTE);
    }

    public static TranslationsFile getDialogPresidentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(DIALOG_PRESIDENT);
    }

    public static TranslationsFile getDialogTarotTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(DIALOG_TAROT);
    }

    public static TranslationsFile getDialogNicknameTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(DIALOG_NICKNAME);
    }

    public static TranslationsFile getDialogSoftTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(DIALOG_SOFT);
    }

    public static TranslationsFile getDialogDisplayTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(DIALOG_DISPLAY);
    }

    public static TranslationsFile getDialogHelpTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(DIALOG_HELP);
    }

    public static TranslationsFile getMainGame(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(MAIN_GAME);
    }

    public static TranslationsFile getSimuGame(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(SIMU_GAME);
    }

    public static TranslationsFile getMenus(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(MENUS);
    }

    public static TranslationsFile getMulti(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(NETWORK);
    }

    public static TranslationsFile getCommonBeloteTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(COMMON_BELOTE);
    }

    public static TranslationsFile getCommonPresidentTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(COMMON_PRESIDENT);
    }

    public static TranslationsFile getCommonTarotTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(COMMON_TAROT);
    }

    public static TranslationsFile getCommonCardsTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(COMMON_CARDS);
    }

    public static TranslationsFile getCommonFileTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(COMMON_FILE);
    }

    public static TranslationsFile getCommonMixTr(TranslationsAppli _lgs) {
        return _lgs.getMapping().getVal(COMMON_MIX);
    }

    public static TranslationsFile en() {
        TranslationsFile e_ = new TranslationsFile();
        e_.add(USER,"Your_nickname");
        e_.add(NICKNAME,"Player {0}");
        return e_;
    }

    public static TranslationsFile fr() {
        TranslationsFile f_ = new TranslationsFile();
        f_.add(USER,"Votre_pseudo");
        f_.add(NICKNAME,"Joueur {0}");
        return f_;
    }
}
