package aiki.game.params;
import code.util.annot.RwXml;

@RwXml
public final class LoadingGame {

//    public static final String DEFAULT_SAVE_GAME = "last_save"+Resources.GAME_EXT;
    public static final String DEFAULT_SAVE_GAME = "last_save";

    private String lastRom = "rom.zip";

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

    public boolean loadRomAndGame() {
        if (loadLastGame) {
            return true;
        }
        if (saveGameAtExit) {
            return true;
        }
        return false;
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
}
