package code.expressionlanguage.types;

import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class ArraryPartType extends ParentPartType {

    ArraryPartType(ParentPartType _parent, int _index) {
        super(_parent, _index);
    }

    String getBegin() {
        return Templates.ARR_BEG_STRING;
    }

    @Override
    void setAnalyzedType(CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

}
