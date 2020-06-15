package code.expressionlanguage.types;

import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class InnerPartType extends BinaryType {

    private CustList<String> operators;

    InnerPartType(ParentPartType _parent, int _index, CustList<String> _operators) {
        super(_parent, _index);
        operators = _operators;
    }

    @Override
    void setAnalyzedType(CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        CustList<PartType> ch_ = new CustList<PartType>();
        PartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = ch_.last().getAnalyzedType();
        setAnalyzedType(t_);
    }

    public CustList<String> getOperators() {
        return operators;
    }
}
