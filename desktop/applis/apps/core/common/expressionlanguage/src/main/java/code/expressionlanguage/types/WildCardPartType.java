package code.expressionlanguage.types;

import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class WildCardPartType extends ParentPartType {

    private String prefix;
    WildCardPartType(ParentPartType _parent, int _index, String _prefix) {
        super(_parent, _index);
        prefix = _prefix;
    }

    String getBegin() {
        return prefix;
    }

    @Override
    void setAnalyzedType(CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
    }

}
