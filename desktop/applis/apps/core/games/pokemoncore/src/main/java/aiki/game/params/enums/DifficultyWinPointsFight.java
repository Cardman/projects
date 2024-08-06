package aiki.game.params.enums;


import aiki.db.*;
import code.util.CustList;
import code.util.core.StringUtil;

public enum DifficultyWinPointsFight {
    TRES_FACILE(DataBase.DEF_TRES_FACILE),FACILE(DataBase.DEF_FACILE),DIFFICILE(DataBase.DEF_DIFFICILE),TRES_DIFFICILE(DataBase.DEF_TRES_DIFFICILE);
    private final String winName;
    DifficultyWinPointsFight(String _w){
        winName= _w;
    }
    public static DifficultyWinPointsFight getDiffWonPtsByName(String _env) {
        for (DifficultyWinPointsFight e: DifficultyWinPointsFight.all()) {
            if (StringUtil.quickEq(e.winName, _env)) {
                return e;
            }
        }
        return DifficultyWinPointsFight.TRES_FACILE;
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
