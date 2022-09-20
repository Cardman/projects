package aiki.game.params.enums;


import code.util.CustList;

public enum DifficultyWinPointsFight {
    TRES_FACILE("TRES_FACILE"),FACILE("FACILE"),DIFFICILE("DIFFICILE"),TRES_DIFFICILE("TRES_DIFFICILE");
    private final String winName;
    DifficultyWinPointsFight(String _w){
        winName= _w;
    }

    public static CustList<DifficultyWinPointsFight> all() {
        CustList<DifficultyWinPointsFight> targets_ = new CustList<DifficultyWinPointsFight>();
        targets_.add(TRES_FACILE);
        targets_.add(FACILE);
        targets_.add(DIFFICILE);
        targets_.add(TRES_DIFFICILE);
        return targets_;
    }

    public String getWinName() {
        return winName;
    }
}
