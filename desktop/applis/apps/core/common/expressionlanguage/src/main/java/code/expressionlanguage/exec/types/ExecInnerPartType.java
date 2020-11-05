package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.util.CustList;

final class ExecInnerPartType extends ExecBinaryType {

    private CustList<String> operators;
    ExecInnerPartType(ExecParentPartType _parent, int _index, CustList<String> _operators, String _previousOperator) {
        super(_parent, _index, _previousOperator);
        operators = _operators;
    }

    @Override
    String getPrettyBegin() {
        return EMPTY_STRING;
    }
    @Override
    String getBegin() {
        return EMPTY_STRING;
    }

    @Override
    String getPrettyEnd() {
        return EMPTY_STRING;
    }
    @Override
    String getEnd() {
        return EMPTY_STRING;
    }

    @Override
    boolean analyzeTree(ContextEl _an) {
        CustList<ExecPartType> ch_ = getChildren();
        String t_ = ch_.last().getAnalyzedType();
        setAnalyzedType(t_);
        return true;
    }

    public CustList<String> getOperators() {
        return operators;
    }
}
