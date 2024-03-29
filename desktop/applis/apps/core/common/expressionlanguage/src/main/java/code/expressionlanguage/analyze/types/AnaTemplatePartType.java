package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.errors.AnalysisMessages;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.DimComp;
import code.maths.litteralcom.StrTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.inherits.Mapping;

import code.util.*;
import code.util.core.StringUtil;

final class AnaTemplatePartType extends AnaBinaryType {
    private final Ints indexesChildConstraints = new Ints();
    private int countParam;
    private boolean koConstraints;

    AnaTemplatePartType(AnaParentPartType _parent, int _index, int _indexInType, StrTypes _operators, AnalysisMessages _messages, StrTypes _values) {
        super(_parent, _index, _indexInType,_operators, _messages, _values);
    }

    String getBegin() {
        return EMPTY_STRING;
    }

    private String getSeparator(int _index) {
        if (_index == 0) {
            return StringExpUtil.TEMPLATE_BEGIN;
        }
        return StringExpUtil.TEMPLATE_SEP;
    }

    String getEnd() {
        return StringExpUtil.TEMPLATE_END;
    }
    @Override
    void analyze(AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaTmp(_loc);
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaTmp(_loc);
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page, int _loc) {
        anaTmp(_loc);
    }

    private void anaTmp(int _page) {
        setLoc(_page);
        CustList<AnaPartType> ch_ = new CustList<AnaPartType>();
        AnaPartType f_ = getFirstChild();
        while (f_ != null) {
            if (f_.getAnalyzedType().isEmpty()){
                return;
            }
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = getBegin();
        int len_ = ch_.size() - 1;
        for (int i = 0; i < len_; i++) {
            t_ = StringUtil.concat(t_, ch_.get(i).getAnalyzedType(),getSeparator(i));
        }
        t_ = StringUtil.concat(t_, ch_.last().getAnalyzedType(),getEnd());
        setAnalyzedType(t_);
    }

    boolean okTmp(StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        AnaPartType f_ = getFirstChild();
        String tempCl_ = f_.getAnalyzedType();
        String tempClFull_ = fetchTemplate();
        tempCl_ = StringExpUtil.getIdFromAllTypes(tempCl_);
        AnaGeneType type_ = _page.getAnaGeneType(tempCl_);
        CustList<StringList> boundsAll_ = AnaInherits.getBoundAll(type_);
        int i_ = 0;
        for (StringList t: boundsAll_) {
            countParam++;
            f_ = f_.getNextSibling();
            if (f_ == null) {
                break;
            }
            String arg_ = f_.getAnalyzedType();
            if (!StringUtil.quickEq(arg_, StringExpUtil.SUB_TYPE) && !arg_.startsWith(StringExpUtil.SUB_TYPE) && !arg_.startsWith(StringExpUtil.SUP_TYPE)) {
                checkArg(_inherit, _page, tempClFull_, type_, i_, t, arg_);
            }
            i_++;
        }
        return indexesChildConstraints.isEmpty();
    }

    private void checkArg(StringMap<StringList> _inherit, AnalyzedPageEl _page, String _tempClFull, AnaGeneType _type, int _i, StringList _t, String _arg) {
        String comp_ = _arg;
        DimComp dimCompArg_ = StringExpUtil.getQuickComponentBaseType(comp_);
        comp_ = dimCompArg_.getComponent();
        boolean lookInInherit_ = comp_.startsWith(AnaInherits.PREFIX_VAR_TYPE);
        StringList bounds_ = new StringList();
        if (lookInInherit_) {
            bounds_.addAllElts(_inherit.getVal(comp_.substring(1)));
        } else {
            bounds_.add(comp_);
        }
        for (String e: _t) {
            Mapping m_ = new Mapping();
            String param_ = AnaInherits.format(_type, _tempClFull, e);
            m_.setParam(param_);
            boolean ok_ = false;
            for (String v: bounds_) {
                m_.setArg(v);
                m_.setMapping(_inherit);
                if (AnaInherits.isCorrect(m_, _page)) {
                    ok_ = true;
                    break;
                }
            }
            if (!ok_) {
                indexesChildConstraints.add(_i);
            }
        }
    }

    Ints getIndexesChildConstraints() {
        return indexesChildConstraints;
    }

    int getCountParam() {
        return countParam;
    }

    private String fetchTemplate() {
        AnaPartType f_ = getFirstChild();
        CustList<AnaPartType> ch_ = new CustList<AnaPartType>();
        while (f_ != null) {
            ch_.add(f_);
            f_ = f_.getNextSibling();
        }
        String t_ = getBegin();
        int len_ = ch_.size() - 1;
        for (int i = 0; i < len_; i++) {
            t_ = StringUtil.concat(t_, ch_.get(i).getAnalyzedType(),getSeparator(i));
        }
        return StringUtil.concat(t_, ch_.last().getAnalyzedType(),getEnd());
    }

    @Override
    int buildErrorInexistBegin() {
        return getFullBegin(0);
    }

    @Override
    int buildErrorInexistEnd() {
        return getOpLen(0);
    }
    boolean isKoConstraints() {
        return koConstraints;
    }

    void setKoConstraints() {
        koConstraints = true;
    }
}
