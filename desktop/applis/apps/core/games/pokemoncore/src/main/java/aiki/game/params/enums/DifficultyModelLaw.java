package aiki.game.params.enums;


import code.util.CustList;
import code.util.core.StringUtil;

public enum DifficultyModelLaw {
    CONSTANT_MIN("CONSTANT_MIN"),CROISSANT("CROISSANT"),UNIFORME("UNIFORME"),DECROISSANT("DECROISSANT"),CONSTANT_MAX("CONSTANT_MAX");
    private final String modelName;
    DifficultyModelLaw(String _m){
        modelName = _m;
    }
    public static DifficultyModelLaw getModelByName(String _env) {
        for (DifficultyModelLaw e: DifficultyModelLaw.all()) {
            if (StringUtil.quickEq(e.modelName, _env)) {
                return e;
            }
        }
        return DifficultyModelLaw.UNIFORME;
    }
    public static CustList<DifficultyModelLaw> all() {
        CustList<DifficultyModelLaw> targets_ = new CustList<DifficultyModelLaw>();
        targets_.add(CONSTANT_MIN);
        targets_.add(CROISSANT);
        targets_.add(UNIFORME);
        targets_.add(DECROISSANT);
        targets_.add(CONSTANT_MAX);
        return targets_;
    }

    public String getModelName() {
        return modelName;
    }
}
