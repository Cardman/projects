package aiki.game.params.enums;
import code.serialize.CheckedData;
import code.util.ints.Listable;


@CheckedData
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
}
