package cards.gui.dialogs;

import code.scripts.imgs.cards.CardsInit;

/**
    */

public final class FileConst {

    public static final String PORT_INI = "cards_port.ini";

    //    public static final String SUITS_PNG = "suits.png";

    public static final String SUITS_TXT = "suits.txt";

    public static final String PNG_EXT = ".png";
    public static final String TXT_EXT = CardsInit.EXT;
    public static final String XML_EXT = ".xml";
    public static final String GAME_EXT=".cdgame";
    public static final String SAVE_FOLDER="Sauvegardes";

    public static final String COORDS="cards.coords";

    public static final String FOLDER_MESSAGES_GUI = "resources_cards/gui/messages";
    public static final String RESOURCES_IMAGES = CardsInit.FOLDER;
    public static final String RESOURCES_HELP = "resources_cards/help";
//    public static final String RESOURCES_HELP_TMP = "resources_cards/help_tmp";
//    public static final String RESOURCES_HTML_FOLDER = "resources_cards";

    public static final String RESOURCES_HTML_FILES_RULES_BELOTE = "resources_cards/conf/rules_belote.xml";
    public static final String RESOURCES_HTML_FILES_RULES_PRESIDENT = "resources_cards/conf/rules_president.xml";
    public static final String RESOURCES_HTML_FILES_RULES_TAROT = "resources_cards/conf/rules_tarot.xml";

    public static final String RESOURCES_HTML_FILES_RESULTS_BELOTE = "resources_cards/conf/results_belote.xml";
    public static final String RESOURCES_HTML_FILES_RESULTS_PRESIDENT = "resources_cards/conf/results_president.xml";
    public static final String RESOURCES_HTML_FILES_RESULTS_TAROT = "resources_cards/conf/results_tarot.xml";

    public static final String RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE = "resources_cards/conf/details_results_belote.xml";
    public static final String RESOURCES_HTML_FILES_DETAILS_RESULTS_PRESIDENT = "resources_cards/conf/details_results_president.xml";
    public static final String RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT = "resources_cards/conf/details_results_tarot.xml";

    public static final int MIN_DEALS = 1;
    public static final int MAX_DEALS = 100;

    private FileConst() {
    }
}
