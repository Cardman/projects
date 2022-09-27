package aiki.fight.pokemon.enums;


import code.util.CustList;
import code.util.core.StringUtil;

public enum ExpType {
    E("E"), F("F"), L("L"), M("M"), P("P"), R("R");
    private final String expName;
    ExpType(String _e) {
        expName= _e;
    }

    public static ExpType getExpTypeByName(String _env) {
        for (ExpType e: ExpType.all()) {
            if (StringUtil.quickEq(e.expName, _env)) {
                return e;
            }
        }
        return ExpType.M;
    }

    public static CustList<ExpType> all() {
        CustList<ExpType> targets_ = new CustList<ExpType>();
        targets_.add(E);
        targets_.add(F);
        targets_.add(L);
        targets_.add(M);
        targets_.add(P);
        targets_.add(R);
        return targets_;
    }
}
