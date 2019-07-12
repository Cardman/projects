package aiki.game.params.enums;
import code.util.StringList;
import code.util.ints.Listable;


public enum DifficultyModelLaw {
    CONSTANT_MIN,CROISSANT,UNIFORME,DECROISSANT,CONSTANT_MAX;
    public static DifficultyModelLaw getModelByName(String _env) {
        for (DifficultyModelLaw e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return UNIFORME;
    }
}
