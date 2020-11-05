package code.expressionlanguage.analyze.types;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.StrTypes;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;

import code.expressionlanguage.analyze.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.*;
import code.util.core.StringUtil;

final class AnaTemplatePartType extends AnaBinaryType {
    private Ints indexesChildConstraints = new Ints();
    private PartOffset lastPartBegin = new PartOffset("",0);
    private PartOffset lastPartEnd = new PartOffset("",0);

    AnaTemplatePartType(AnaParentPartType _parent, int _index, int _indexInType, StrTypes _operators) {
        super(_parent, _index, _indexInType,_operators);
    }

    String getBegin() {
        return EMPTY_STRING;
    }

    private String getSeparator(int _index) {
        if (_index == 0) {
            return Templates.TEMPLATE_BEGIN;
        }
        return Templates.TEMPLATE_SEP;
    }

    String getEnd() {
        return Templates.TEMPLATE_END;
    }
    @Override
    void analyze(String _globalType, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        analyzeLine(null, _local,_rooted, null);
    }

    @Override
    void analyzeLine(ReadyTypes _ready, AccessedBlock _local, AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaTmp();
    }

    @Override
    void analyzeAccessibleId(AccessedBlock _rooted, AnalyzedPageEl _page) {
        anaTmp();
    }

    private void anaTmp() {
        CustList<AnaPartType> ch_ = new CustList<AnaPartType>();
        AnaPartType f_ = getFirstChild();
        while (f_ != null) {
            if (f_.getAnalyzedType().isEmpty()){
                setAlreadyError();
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
        CustList<StringList> boundsAll_ = AnaTemplates.getBoundAll(type_);
        int i_ = 0;
        for (StringList t: boundsAll_) {
            getBeginOps().add(new PartOffset("",0));
            getEndOps().add(new PartOffset("",0));
            getErrsList().add(new StringList());
            f_ = f_.getNextSibling();
            if (f_ == null) {
                break;
            }
            String arg_ = f_.getAnalyzedType();
            if (StringUtil.quickEq(arg_, Templates.SUB_TYPE)) {
                i_++;
                continue;
            }
            if (arg_.startsWith(Templates.SUB_TYPE)) {
                i_++;
                continue;
            }
            if (arg_.startsWith(Templates.SUP_TYPE)) {
                i_++;
                continue;
            }
            String comp_ = arg_;
            DimComp dimCompArg_ = StringExpUtil.getQuickComponentBaseType(comp_);
            comp_ = dimCompArg_.getComponent();
            boolean lookInInherit_ = comp_.startsWith(AnaTemplates.PREFIX_VAR_TYPE);
            StringList bounds_ = new StringList();
            if (lookInInherit_) {
                bounds_.addAllElts(_inherit.getVal(comp_.substring(1)));
            } else {
                bounds_.add(comp_);
            }
            for (String e: t) {
                Mapping m_ = new Mapping();
                String param_ = AnaTemplates.format(type_,tempClFull_, e);
                m_.setParam(param_);
                boolean ok_ = false;
                for (String v: bounds_) {
                    m_.setArg(v);
                    m_.setMapping(_inherit);
                    if (AnaTemplates.isCorrect(m_, _page)) {
                        ok_ = true;
                        break;
                    }
                }
                if (!ok_) {
                    indexesChildConstraints.add(i_);
                }
            }
            i_++;
        }
        return indexesChildConstraints.isEmpty();
    }

    void buildBadConstraintsOffsetList(AnalyzedPageEl _page) {
        if (!_page.isGettingParts()) {
            return;
        }
        for (int i: indexesChildConstraints) {
            String err_ = FoundErrorInterpret.buildARError(_page.getAnalysisMessages().getBadParamerizedType(), getAnalyzedType());
            getErrsList().get(i).add(err_);
        }
        for (int i: indexesChildConstraints) {
            int begin_ = _page.getLocalInType() + getIndexInType() + getOperators().getKey(i);
            int len_ = getOperators().getValue(i).length();
            String err_ = LinkageUtil.transform(StringUtil.join(getErrsList().get(i),"\n\n"));
            getBeginOps().set(i,new PartOffset("<a title=\""+err_+"\" class=\"e\">",begin_));
            getEndOps().set(i,new PartOffset("</a>",begin_+len_));
        }
    }

    void processBadLen(AnalyzedPageEl _page) {
        int begin_ = _page.getLocalInType() + getIndexInType() + getOperators().lastKey();
        int len_ = getOperators().lastValue().length();
        StringList errLen_ = getErrs();
        if (errLen_.isEmpty()) {
            return;
        }
        String err_ = LinkageUtil.transform(StringUtil.join(errLen_,"\n\n"));
        lastPartBegin=(new PartOffset("<a title=\""+err_+"\" class=\"e\">",begin_));
        lastPartEnd=(new PartOffset("</a>",begin_+len_));
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
    void buildErrorInexist(AnalyzedPageEl _page) {
        int begin_ = _page.getLocalInType() + getIndexInType() + getOperators().firstKey();
        int len_ = getOperators().firstValue().length();
        buildOffsetPart(begin_,len_);
    }

    PartOffset getLastPartBegin() {
        return lastPartBegin;
    }

    PartOffset getLastPartEnd() {
        return lastPartEnd;
    }
}
