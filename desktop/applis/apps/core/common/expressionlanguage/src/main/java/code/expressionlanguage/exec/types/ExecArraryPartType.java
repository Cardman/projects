package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

final class ExecArraryPartType extends ExecParentPartType {
    ExecArraryPartType(ExecParentPartType _parent, int _index) {
        super(_parent, _index);
    }

    @Override
    String getPrettyBegin() {
        return EMPTY_STRING;
    }

    @Override
    String getBegin() {
        return Templates.ARR_BEG_STRING;
    }

    @Override
    String getPrettyEnd() {
        return "[]";
    }
    @Override
    String getEnd() {
        return EMPTY_STRING;
    }

    @Override
    boolean analyzeTree(ContextEl _an, CustList<IntTreeMap<String>> _dels) {
        String ch_ = getFirstChild().getAnalyzedType();
        ch_ = StringList.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
        return true;
    }
}
