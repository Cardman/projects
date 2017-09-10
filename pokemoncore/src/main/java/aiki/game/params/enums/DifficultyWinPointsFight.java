package aiki.game.params.enums;
import code.serialize.CheckedData;
import code.util.ints.Listable;


@CheckedData
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
}
