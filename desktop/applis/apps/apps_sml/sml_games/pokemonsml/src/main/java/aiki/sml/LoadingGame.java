package aiki.sml;


public final class LoadingGame {

//    public static final String DEFAULT_SAVE_GAME = "last_save"+Resources.GAME_EXT;
    public static final String DEFAULT_SAVE_GAME = "last_save.pkgame";
    public static final String PORT_INI = "pokemon_port.ini";
//    public static final String CONF_EXT = ".pkconf";
    public static final String LOAD_CONFIG_FILE = "loadconfig.pkconf";
//    public static final String MESSAGES_FOLDER= "resources_pk/gui/messages";
    public static final String COORDS="pk.coords";

    private String lastRom = "";

    private String lastSavedGame = "";

    private boolean saveHomeFolder = true;

    private boolean loadHomeFolder;

    private boolean loadLastRom = true;

    private boolean loadLastGame;

    private boolean saveGameAtExit = true;

    private boolean enableAnimation = true;

    private boolean enableMovingHerosAnimation = true;

    private boolean clickButtonsPad = true;

    private boolean enabledKeyPad = true;

    private String export = "";

    public boolean loadRomAndGame() {
        if (loadLastGame) {
            return true;
        }
        return saveGameAtExit;
    }

    public String getLastRom() {
        return lastRom;
    }

    public void setLastRom(String _lastRom) {
        lastRom = _lastRom;
    }

    public String getLastSavedGame() {
        return lastSavedGame;
    }

    public void setLastSavedGame(String _lastSavedGame) {
        lastSavedGame = _lastSavedGame;
    }

    public boolean isSaveHomeFolder() {
        return saveHomeFolder;
    }

    public void setSaveHomeFolder(boolean _saveHomeFolder) {
        saveHomeFolder = _saveHomeFolder;
    }

    public boolean isLoadHomeFolder() {
        return loadHomeFolder;
    }

    public void setLoadHomeFolder(boolean _loadHomeFolder) {
        loadHomeFolder = _loadHomeFolder;
    }

    public boolean isLoadLastRom() {
        return loadLastRom;
    }

    public void setLoadLastRom(boolean _loadLastRom) {
        loadLastRom = _loadLastRom;
    }

    public boolean isLoadLastGame() {
        return loadLastGame;
    }

    public void setLoadLastGame(boolean _loadLastGame) {
        loadLastGame = _loadLastGame;
    }

    public boolean isSaveGameAtExit() {
        return saveGameAtExit;
    }

    public void setSaveGameAtExit(boolean _saveGameAtExit) {
        saveGameAtExit = _saveGameAtExit;
    }

    public boolean isEnableAnimation() {
        return enableAnimation;
    }

    public void setEnableAnimation(boolean _enableAnimation) {
        enableAnimation = _enableAnimation;
    }

    public boolean isEnableMovingHerosAnimation() {
        return enableMovingHerosAnimation;
    }

    public void setEnableMovingHerosAnimation(boolean _enableMovingHerosAnimation) {
        enableMovingHerosAnimation = _enableMovingHerosAnimation;
    }

    public boolean isClickButtonsPad() {
        return clickButtonsPad;
    }

    public void setClickButtonsPad(boolean _clickButtons) {
        clickButtonsPad = _clickButtons;
    }

    public boolean isEnabledKeyPad() {
        return enabledKeyPad;
    }

    public void setEnabledKeyPad(boolean _enabledKeyPad) {
        enabledKeyPad = _enabledKeyPad;
    }

    public String getExport() {
        return export;
    }

    public void setExport(String _export) {
        this.export = _export;
    }
}
