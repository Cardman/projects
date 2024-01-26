package cards.consts;


import code.util.IdList;

/**Type de partie joue*/
public enum GameType {
    EDIT("1"),TRAINING("2"),RANDOM("0");
    private final String gameTypeSt;

    GameType(String _g) {
        this.gameTypeSt = _g;
    }

    public String getGameTypeSt() {
        return gameTypeSt;
    }
    public static IdList<GameType> all() {
        IdList<GameType> all_ = new IdList<GameType>();
        all_.add(EDIT);
        all_.add(TRAINING);
        all_.add(RANDOM);
        return all_;
    }
}
