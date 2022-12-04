package cards.network.common;


public class PlayerActionGame {

    private final PlayerActionGameType actionType;

    private byte place;

    private String locale;

    public PlayerActionGame() {
        actionType = PlayerActionGameType.SIMPLE;
    }

    public PlayerActionGame(PlayerActionGameType _actionType) {
        actionType = _actionType;
    }

    public PlayerActionGameType getActionType() {
        return actionType;
    }

    public byte getPlace() {
        return place;
    }

    public void setPlace(byte _place) {
        place = _place;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String _locale) {
        locale = _locale;
    }
}
