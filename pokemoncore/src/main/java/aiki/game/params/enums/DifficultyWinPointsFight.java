package aiki.game.params.enums;
import code.util.StringList;
import code.util.ints.Listable;


public enum DifficultyWinPointsFight {
    TRES_FACILE,FACILE,DIFFICILE,TRES_DIFFICILE;
    public static DifficultyWinPointsFight getDiffWonPtsByName(String _env) {
        for (DifficultyWinPointsFight e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return TRES_FACILE;
    }
}
