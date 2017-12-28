package aiki;

import code.util.StringList;

public final class Resources {

    public static final String PORT_INI = "pokemon_port.ini";

    public static final String EXCLUDED = "jre";

    public static final String ZIPPED_DATA_EXT = ".zip";

    public static final String GAME_EXT = ".pkgame";

    public static final String CONF_EXT = ".pkconf";

    public static final String LOAD_CONFIG_FILE = "loadconfig.pkconf";

    public static final String RESOURCES_FOLDER= "resources_pk";

//    public static final String ICON = "pokemon.png";

    public static final String ICON_TXT = "pokemon.txt";

    public static final String MESSAGES_FOLDER= "resources_pk/gui/messages";

    public static final String COORDS="pk.coords";

    public static final String ACCESS_TO_DEFAULT_FILES = "resources_pk/rom/";

    public static final String CONFIG_DATA = "web/faces.xml";

    public static final String CONFIG_DIFF = "web_game/faces.xml";

    public static final String CONFIG_PROG = "web_prog/faces.xml";

    public static final String CONFIG_PK = "web_pk/faces_pokemon.xml";

    public static final String CONFIG_FIGHT = "web_fight/faces.xml";

    public static final String ACCESS_TO_DEFAULT_FILES_DATA = "resources_pk/rom/web";

    public static final String ACCESS_TO_DEFAULT_FILES_PROG = "resources_pk/rom/web_prog";

    public static final String ACCESS_TO_DEFAULT_FILES_DIFF = "resources_pk/rom/web_game";

    public static final String ACCESS_TO_DEFAULT_FILES_PK = "resources_pk/rom/web_pk";

    public static final String ACCESS_TO_DEFAULT_FILES_FIGHT = "resources_pk/rom/web_fight";

    public static final String ACCESS_TO_DEFAULT_DATA = StringList.concat(ACCESS_TO_DEFAULT_FILES,CONFIG_DATA);

    public static final String ACCESS_TO_DEFAULT_DIFF = StringList.concat(ACCESS_TO_DEFAULT_FILES,CONFIG_DIFF);

    public static final String ACCESS_TO_DEFAULT_PROG = StringList.concat(ACCESS_TO_DEFAULT_FILES,CONFIG_PROG);

    public static final String ACCESS_TO_DEFAULT_PK = StringList.concat(ACCESS_TO_DEFAULT_FILES,CONFIG_PK);

    public static final String ACCESS_TO_DEFAULT_FIGHT = StringList.concat(ACCESS_TO_DEFAULT_FILES,CONFIG_FIGHT);

    private Resources(){
    }
}
