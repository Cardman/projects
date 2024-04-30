package aiki.game.fight.enums;


public enum ActionType {
    MOVE("0"),SWITCH("1"),HEALING("2"),NOTHING("3");

    private final String keyAction;
    ActionType(String _key) {
        keyAction = _key;
    }

    public String getKeyAction() {
        return keyAction;
    }
}
