package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessedBlock;
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
    boolean analyzeTree(ExecutableCode _an, CustList<IntTreeMap< String>> _dels) {
        return true;
    }

    @Override
    void analyze(Analyzable _an, CustList<IntTreeMap< String>>_dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
        analyzeLine(_an,null,_dels,_globalType,_local,_rooted);
    }

    @Override
    void analyzeTemplate(Analyzable _an, CustList<IntTreeMap<String>> _dels, StringMap<StringList> _inherit) {
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
    void analyzeLine(Analyzable _an, ReadyTypes _ready,CustList<IntTreeMap< String>>_dels, String _globalType, AccessingImportingBlock _local,AccessingImportingBlock _rooted) {
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
    void analyzeAccessibleId(Analyzable _an,
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
