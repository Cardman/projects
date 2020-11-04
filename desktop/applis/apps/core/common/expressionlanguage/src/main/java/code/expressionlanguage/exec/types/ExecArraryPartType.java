package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StrTypes;
import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.core.StringUtil;

final class ExecArraryPartType extends ExecParentPartType {
    ExecArraryPartType(ExecParentPartType _parent, int _index, String _previousOperator) {
        super(_parent, _index, _previousOperator);
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
    boolean analyzeTree(ContextEl _an, CustList<StrTypes> _dels) {
        String ch_ = getChildren().first().getAnalyzedType();
        ch_ = StringUtil.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
        return true;
    }
}
