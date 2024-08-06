package aiki.fight.pokemon.enums;


import aiki.db.DataBase;
import code.util.CustList;
import code.util.core.StringUtil;

public enum ExpType {
    E(DataBase.DEF_EXP_E), F(DataBase.DEF_EXP_F), L(DataBase.DEF_EXP_L), M(DataBase.DEF_EXP_M), P(DataBase.DEF_EXP_P), R(DataBase.DEF_EXP_R);
    private final String expName;
    ExpType(String _e) {
        expName= _e;
    }

    public static ExpType getExpTypeByName(String _env) {
        for (ExpType e: ExpType.all()) {
            if (StringUtil.quickEq(e.getExpName(), _env)) {
                return e;
            }
        }
        return ExpType.M;
    }

    public String getExpName() {
        return expName;
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
