package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.util.CustList;
import code.util.IntTreeMap;
import code.util.core.StringUtil;

class ExecInnerPartType extends ExecBinaryType {

    private CustList<String> operators;
    ExecInnerPartType(ExecParentPartType _parent, int _index, CustList<String> _operators) {
        super(_parent, _index);
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
    String getSeparator(int _index) {
        return operators.get(_index);
    }

    @Override
    String getSingleSeparator(int _index) {
        if (StringUtil.quickEq(operators.get(_index),"..")) {
            return ".";
        }
        return "..";
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
    boolean analyzeTree(ContextEl _an, CustList<IntTreeMap<String>> _dels) {
        CustList<ExecPartType> ch_ = new CustList<ExecPartType>();
        ExecPartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = ch_.last().getAnalyzedType();
        setAnalyzedType(t_);
        return true;
    }

    public CustList<String> getOperators() {
        return operators;
    }
}
