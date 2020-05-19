package code.expressionlanguage.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.util.CustList;
import code.util.*;
import code.util.StringList;

final class InnerPartType extends BinaryType {

    private CustList<String> operators;

    InnerPartType(ParentPartType _parent, int _index, int _indexInType, CustList<String> _operators) {
        super(_parent, _index, _indexInType);
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
        if (StringList.quickEq(operators.get(_index),"..")) {
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
    boolean analyzeTree(ContextEl _an, CustList<IntTreeMap< String>> _dels) {
        return true;
    }

    @Override
    void analyze(ContextEl _an, CustList<IntTreeMap< String>>_dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        analyzeLine(_an,null,_dels,_globalType,_local,_rooted);
    }

    @Override
    void analyzeTemplate(ContextEl _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
        CustList<PartType> ch_ = new CustList<PartType>();
        PartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = ch_.last().getAnalyzedType();
        setAnalyzedType(t_);
    }

    @Override
    void analyzeLine(ContextEl _an, ReadyTypes _ready,CustList<IntTreeMap< String>>_dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        CustList<PartType> ch_ = new CustList<PartType>();
        PartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = ch_.last().getAnalyzedType();
        setAnalyzedType(t_);
    }

    @Override
    void analyzeAccessibleId(ContextEl _an,
            CustList<IntTreeMap< String>> _dels,
                             AccessingImportingBlock _rooted) {
        CustList<PartType> ch_ = new CustList<PartType>();
        PartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = ch_.last().getAnalyzedType();
        setAnalyzedType(t_);
    }

    public CustList<String> getOperators() {
        return operators;
    }
}
