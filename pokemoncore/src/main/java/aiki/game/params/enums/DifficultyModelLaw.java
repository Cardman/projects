package aiki.game.params.enums;
import code.util.StringList;
import code.util.ints.Listable;


public enum DifficultyModelLaw {
    CONSTANT_MIN,CROISSANT,UNIFORME,DECROISSANT,CONSTANT_MAX;
    public static boolean equalsSet(Listable<DifficultyModelLaw> _list1,Listable<DifficultyModelLaw> _list2) {
        for (DifficultyModelLaw a: _list2) {
            boolean contains_ = false;
            for (DifficultyModelLaw b: _list1) {
                if (a == b) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (DifficultyModelLaw a: _list1) {
            boolean contains_ = false;
            for (DifficultyModelLaw b: _list2) {
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
    public static DifficultyModelLaw getModelByName(String _env) {
        for (DifficultyModelLaw e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return null;
    }
}
