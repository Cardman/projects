package code.expressionlanguage.types;

import code.util.CustList;
import code.util.*;
import code.util.StringList;


abstract class PartType {

    protected static final String EMPTY_STRING = "";
    private ParentPartType parent;
    private PartType previousSibling;
    private PartType nextSibling;
    private int index;
    private String analyzedType = EMPTY_STRING;

    PartType(ParentPartType _parent, int _index) {
        parent = _parent;
        index = _index;
    }

    static PartType createQuickPartType(ParentPartType _parent, int _index, AnalyzingType _analyze, IntTreeMap<String> _dels) {
        IntTreeMap<String> operators_ = _analyze.getOperators();
        if (_analyze.isError()) {
            return new EmptyPartType(_parent, _index, _dels.getValue(_index),"");
        }
        if (operators_.isEmpty()) {
            String str_ = "..";
            if (_parent instanceof InnerPartType && _index > 0) {
                str_ = ((InnerPartType) _parent).getOperators().get(_index - 1);
            }
            if (_analyze.getKind() == KindPartType.TYPE_NAME) {
                String type_ = _dels.getValue(_index);
                return new NamePartType(_parent, _index, type_,str_);
            }
            if (_analyze.getKind() == KindPartType.EMPTY_WILD_CARD) {
                return new EmptyWildCardPart(_parent, _index, _dels.getValue(_index),str_);
            }
            return new VariablePartType(_parent, _index, _dels.getValue(_index),str_);
        }
        if (_analyze.getPrio() == ParserType.TMP_PRIO) {
            return new TemplatePartType(_parent, _index);
        }
        if (_analyze.getPrio() == ParserType.INT_PRIO) {
            return new InnerPartType(_parent, _index, operators_.values());
        }
        if (_analyze.getPrio() == ParserType.ARR_PRIO) {
            return new ArraryPartType(_parent, _index);
        }
        return new WildCardPartType(_parent, _index, operators_.firstValue());
    }

    abstract void setAnalyzedType(CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit);

    int getIndex() {
        return index;
    }

    final ParentPartType getParent() {
        return parent;
    }
    final PartType getNextSibling() {
        return nextSibling;
    }
    final void setPreviousSibling(PartType _previousSibling) {
        previousSibling = _previousSibling;
    }
    final PartType getPreviousSibling() {
        return previousSibling;
    }
    abstract PartType getFirstChild();
    void setNextSibling(PartType _child) {
        nextSibling = _child;
    }
    String getAnalyzedType() {
        return analyzedType;
    }
    void setAnalyzedType(String _analyzedType) {
        analyzedType = _analyzedType;
    }
}
