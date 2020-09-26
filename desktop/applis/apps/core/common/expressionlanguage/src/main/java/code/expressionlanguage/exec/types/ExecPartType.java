package code.expressionlanguage.exec.types;

import code.expressionlanguage.types.KindPartType;
import code.util.IntTreeMap;

abstract class ExecPartType {
    protected static final String EMPTY_STRING = "";
    private ExecParentPartType parent;
    private ExecPartType previousSibling;
    private ExecPartType nextSibling;
    private int index;
    private String analyzedType = EMPTY_STRING;

    ExecPartType(ExecParentPartType _parent, int _index) {
        parent = _parent;
        index = _index;
    }

    static ExecPartType createPartTypeExec(ExecParentPartType _parent, int _index, ExecAnalyzingType _analyze, IntTreeMap<String> _dels) {
        if (_analyze.isError()) {
            return new ExecEmptyPartType(_parent, _index, _dels.getValue(_index),"");
        }
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (operators_.isEmpty()) {
            String str_ = "..";
            if (_parent instanceof ExecInnerPartType && _index > 0) {
                str_ = ((ExecInnerPartType) _parent).getOperators().get(_index - 1);
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new ExecEmptyWildCardPart(_parent, _index, _dels.getValue(_index),str_);
            }
            return new ExecNamePartType(_parent, _index, _dels.getValue(_index),str_);
        }
        if (_analyze.getPrio() == ExecPartTypeUtil.TMP_PRIO) {
            return new ExecTemplatePartType(_parent, _index);
        }
        if (_analyze.getPrio() == ExecPartTypeUtil.INT_PRIO) {
            return new ExecInnerPartType(_parent, _index, operators_.values());
        }
        if (_analyze.getPrio() == ExecPartTypeUtil.ARR_PRIO) {
            return new ExecArraryPartType(_parent, _index);
        }
        return new ExecWildCardPartType(_parent, _index, operators_.firstValue());
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

}
