package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.AbstractReplacingType;
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
        return AbstractReplacingType.ARR_BEG_STRING;
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
    boolean analyzeTree(ContextEl _an) {
        String ch_ = getChildren().first().getAnalyzedType();
        ch_ = StringUtil.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
        return true;
    }
}
