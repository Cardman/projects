package cards.solitaire;

import code.util.*;
import code.util.core.*;

public enum SolitaireType {
    CLASSIC(0,4),FREECELL(1,8), SPIDER(2,1);

    private final int kind;
    private final int supp;
    SolitaireType(int _k, int _n) {
        kind = _k;
        supp = _n;
    }


    public static SolitaireType getSolitaireTypeByName(String _env) {
        for (SolitaireType e: all()) {
            if (StringUtil.quickEq(Integer.toString(e.getKind()), _env)) {
                return e;
            }
        }
        return null;
    }
    public static CustList<SolitaireType> all() {
        CustList<SolitaireType> ls_ = new CustList<SolitaireType>();
        ls_.add(CLASSIC);
        ls_.add(FREECELL);
        ls_.add(SPIDER);
        return ls_;
    }
    public int getKind() {
        return kind;
    }

    public int getSupp() {
        return supp;
    }
}
