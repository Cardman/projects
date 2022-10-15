package cards.consts.beans;

import cards.consts.LineDeal;
import code.bean.nat.*;
import code.expressionlanguage.structs.AbNullStruct;
import code.util.CustList;
import code.util.Longs;
import code.util.StringMap;

public final class LineDealStruct  extends AbNullStruct {
    static final String TYPE_LINE_DEAL = "cards.beans.LineDeal";
    private static final String SCORES = "scores";
    private static final String NUMBER = "number";
    private final LineDeal lineDeal;
    public LineDealStruct(LineDeal _lineDeal) {
        lineDeal = _lineDeal;
    }

    public LineDeal getLineDeal() {
        return lineDeal;
    }
    public static void buildLineDeal(StringMap<SpecialNatClass> _types) {
        CustList<StandardField> fields_ = new CustList<StandardField>();
        CustList<SpecNatMethod> methods_ = new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_LINE_DEAL, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        fields_.add( new StandardField(NUMBER, BeanNatCommonLgNames.PRIM_INTEGER, false, false, new LineDealNumber(),null));
        fields_.add( new StandardField(SCORES, BeanNatCommonLgNames.TYPE_LIST, false, false, new LineDealScores(),null));
        _types.addEntry(TYPE_LINE_DEAL, std_);
    }

    public static NatArrayStruct getLineDealArray(CustList<LineDeal> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (LineDeal s:_ls) {
            arr_.set(j_,new LineDealStruct(s));
            j_++;
        }
        return arr_;
    }
    public static CustList<Longs> scores(CustList<LineDeal> _lines) {
        CustList<Longs> l_ = new CustList<Longs>();
        for (LineDeal e: _lines) {
            l_.add(e.getScores());
        }
        return l_;
    }
}
