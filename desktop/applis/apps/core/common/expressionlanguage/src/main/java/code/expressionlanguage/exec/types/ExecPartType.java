package code.expressionlanguage.exec.types;

import code.maths.litteralcom.StrTypes;
import code.expressionlanguage.types.KindPartType;
import code.util.CustList;
import code.util.core.StringUtil;

abstract class ExecPartType {
    protected static final String EMPTY_STRING = "";
    private final ExecParentPartType parent;
    private ExecPartType previousSibling;
    private ExecPartType nextSibling;
    private final int index;
    private String analyzedType = EMPTY_STRING;
    private final String previousOperator;
    private final String previousOperatorSingle;
    ExecPartType(ExecParentPartType _parent, int _index, String _previousOperator) {
        parent = _parent;
        index = _index;
        previousOperator = _previousOperator;
        previousOperatorSingle = trOp(_previousOperator);
    }

    static ExecPartType createPartTypeExec(ExecParentPartType _parent, int _index, ExecAnalyzingType _analyze, String _value) {
        String previousOperator_ = EMPTY_STRING;
        if (_parent instanceof ExecInnerPartType) {
            CustList<String> ops_ = ((ExecInnerPartType) _parent).getOperators();
            if (ops_.isValidIndex(_index - 1)) {
                previousOperator_ = ops_.get(_index - 1);
            }
        }
        if (_parent instanceof ExecTemplatePartType) {
            if (_index > 0) {
                if (_index == 1) {
                    previousOperator_ = "<";
                } else {
                    previousOperator_ = ",";
                }
            }
        }
        if (_analyze.isError()) {
            return new ExecEmptyPartType(_parent, _index, _value,"", previousOperator_);
        }
        StrTypes operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = "..";
            if (_parent instanceof ExecInnerPartType) {
                CustList<String> ops_ = ((ExecInnerPartType) _parent).getOperators();
                if (ops_.isValidIndex(_index - 1)) {
                    str_ = ops_.get(_index - 1);
                }
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new ExecEmptyWildCardPart(_parent, _index, _value,str_, previousOperator_);
            }
            return new ExecNamePartType(_parent, _index, _value,str_, previousOperator_);
        }
        if (_analyze.getPrio() == ExecPartTypeUtil.TMP_PRIO) {
            return new ExecTemplatePartType(_parent, _index, previousOperator_);
        }
        if (_analyze.getPrio() == ExecPartTypeUtil.INT_PRIO) {
            return new ExecInnerPartType(_parent, _index, operators_.values(), previousOperator_);
        }
        if (_analyze.getPrio() == ExecPartTypeUtil.ARR_PRIO) {
            return new ExecArraryPartType(_parent, _index, previousOperator_);
        }
        if (StringUtil.quickEq(operators_.firstValue(),"~")) {
            return new ExecRefPartType(_parent, _index, operators_.firstValue(), previousOperator_);
        }
        return new ExecWildCardPartType(_parent, _index, operators_.firstValue(), previousOperator_);
    }

    protected static String trOp(String _op) {
        if (_op.isEmpty()) {
            return "";
        }
        if (StringUtil.quickEq(_op,",")) {
            return ",";
        }
        if (StringUtil.quickEq(_op,"<")) {
            return "<";
        }
        if (StringUtil.quickEq(_op,"..")) {
            return ".";
        }
        return "..";
    }

    int getIndex() {
        return index;
    }

    final ExecParentPartType getParent() {
        return parent;
    }
    final ExecPartType getNextSibling() {
        return nextSibling;
    }
    final void setPreviousSibling(ExecPartType _previousSibling) {
        previousSibling = _previousSibling;
    }
    final ExecPartType getPreviousSibling() {
        return previousSibling;
    }
    abstract ExecPartType getFirstChild();
    void setNextSibling(ExecPartType _child) {
        nextSibling = _child;
    }
    String getAnalyzedType() {
        return analyzedType;
    }
    void setAnalyzedType(String _analyzedType) {
        analyzedType = _analyzedType;
    }

    String getPreviousOperator() {
        return previousOperator;
    }

    String getPreviousOperatorSingle() {
        return previousOperatorSingle;
    }
}
