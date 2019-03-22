package code.expressionlanguage.types;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.methods.AccessingImportingBlock;
import code.expressionlanguage.methods.RootBlock;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.StringList;

final class TemplatePartType extends BinaryType {

    TemplatePartType(ParentPartType _parent, int _index, int _indexInType) {
        super(_parent, _index, _indexInType);
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
        if (_index == 0) {
            return Templates.TEMPLATE_BEGIN;
        }
        return Templates.TEMPLATE_SEP;
    }

    @Override
    String getPrettyEnd() {
        return Templates.TEMPLATE_END;
    }
    @Override
    String getEnd() {
        return Templates.TEMPLATE_END;
    }

    @Override
    boolean analyzeTree(ExecutableCode _an, CustList<NatTreeMap<Integer, String>> _dels) {
        return true;
    }

    @Override
    void analyzeDepends(Analyzable _an,
            int _index, CustList<NatTreeMap<Integer, String>> _dels,
            RootBlock _rooted, boolean _exact) {
        CustList<PartType> ch_ = new CustList<PartType>();
        PartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        StringList types_ = getTypeNames();
        int len_ = ch_.size();
        for (int i = 0; i < len_; i++) {
            types_.addAllElts(ch_.get(i).getTypeNames());
        }
        String t_ = getBegin();
        len_ = ch_.size() - 1;
        for (int i = 0; i < len_; i++) {
            t_ = StringList.concat(t_, ch_.get(i).getAnalyzedType(),getSeparator(i));
        }
        t_ = StringList.concat(t_, ch_.last().getAnalyzedType(),getEnd());
        setAnalyzedType(t_);
    }
    @Override
    public void analyzeInherits(Analyzable _an, int _index,
            CustList<NatTreeMap<Integer, String>> _dels, String _globalType,
            RootBlock _rooted,
            boolean _protected) {
        CustList<PartType> ch_ = new CustList<PartType>();
        PartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = getBegin();
        int len_ = ch_.size() - 1;
        for (int i = 0; i < len_; i++) {
            t_ = StringList.concat(t_, ch_.get(i).getAnalyzedType(),getSeparator(i));
        }
        t_ = StringList.concat(t_, ch_.last().getAnalyzedType(),getEnd());
        setAnalyzedType(t_);
    }
    @Override
    void analyze(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, String _globalType, AccessingImportingBlock _rooted) {
        analyzeLine(_an,_dels,_globalType,_rooted);
    }
    @Override
    void analyzeLine(Analyzable _an, CustList<NatTreeMap<Integer, String>>_dels, String _globalType, AccessingImportingBlock _rooted) {
        CustList<PartType> ch_ = new CustList<PartType>();
        PartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = getBegin();
        int len_ = ch_.size() - 1;
        for (int i = 0; i < len_; i++) {
            t_ = StringList.concat(t_, ch_.get(i).getAnalyzedType(),getSeparator(i));
        }
        t_ = StringList.concat(t_, ch_.last().getAnalyzedType(),getEnd());
        setAnalyzedType(t_);
    }
    @Override
    void analyzeAccessibleId(Analyzable _an,
            CustList<NatTreeMap<Integer, String>> _dels,
            AccessingImportingBlock _rooted) {
        CustList<PartType> ch_ = new CustList<PartType>();
        PartType f_ = getFirstChild();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = getBegin();
        int len_ = ch_.size() - 1;
        for (int i = 0; i < len_; i++) {
            t_ = StringList.concat(t_, ch_.get(i).getAnalyzedType(),getSeparator(i));
        }
        t_ = StringList.concat(t_, ch_.last().getAnalyzedType(),getEnd());
        setAnalyzedType(t_);
    }
}
