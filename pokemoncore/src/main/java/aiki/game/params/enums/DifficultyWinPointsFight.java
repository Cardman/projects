package aiki.game.params.enums;
import code.util.StringList;
import code.util.ints.Listable;


public enum DifficultyWinPointsFight {
    TRES_FACILE,FACILE,DIFFICILE,TRES_DIFFICILE;
    public static boolean equalsSet(Listable<DifficultyWinPointsFight> _list1,Listable<DifficultyWinPointsFight> _list2) {
        for (DifficultyWinPointsFight a: _list2) {
            boolean contains_ = false;
            for (DifficultyWinPointsFight b: _list1) {
                if (a == b) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (DifficultyWinPointsFight a: _list1) {
            boolean contains_ = false;
            for (DifficultyWinPointsFight b: _list2) {
                if (a == b) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }
    public static DifficultyWinPointsFight getDiffWonPtsByName(String _env) {
        for (DifficultyWinPointsFight e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return null;
    }
}
