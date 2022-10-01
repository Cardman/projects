package cards.consts.beans;

import cards.consts.LineDeal;
import code.bean.nat.*;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.ArrayStruct;
import code.util.CustList;
import code.util.StringMap;

public final class LineDealStruct  extends CommNatStruct {
    static final String TYPE_LINE_DEAL = "cards.beans.LineDeal";
    private static final String SCORES = "scores";
    private static final String NUMBER = "number";
    private final LineDeal lineDeal;
    public LineDealStruct(LineDeal _lineDeal, String _className) {
        super(_className);
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

    public static ArrayStruct getLineDealArray(CustList<LineDeal> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_LINE_DEAL));
        int j_ = 0;
        for (LineDeal s:_ls) {
            arr_.set(j_,new LineDealStruct(s, TYPE_LINE_DEAL));
            j_++;
        }
        return arr_;
    }
}
