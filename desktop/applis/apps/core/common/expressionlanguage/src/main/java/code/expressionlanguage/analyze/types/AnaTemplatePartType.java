package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.blocks.AccessedBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;

import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.linkage.LinkageUtil;
import code.util.*;

final class AnaTemplatePartType extends AnaBinaryType {
    private Ints indexesChildConstraints = new Ints();
    private PartOffset lastPartBegin = new PartOffset("",0);
    private PartOffset lastPartEnd = new PartOffset("",0);

    AnaTemplatePartType(AnaParentPartType _parent, int _index, int _indexInType, IntTreeMap<String> _operators) {
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
    void analyze(ContextEl _an, CustList<IntTreeMap<String>> _dels, String _globalType, AccessedBlock _local, AccessedBlock _rooted) {
        analyzeLine(_an,null,_dels,_local,_rooted);
    }

    @Override
    void analyzeLine(ContextEl _an, ReadyTypes _ready, CustList<IntTreeMap<String>> _dels, AccessedBlock _local, AccessedBlock _rooted) {
        anaTmp();
    }

    @Override
    void analyzeAccessibleId(ContextEl _an, CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted) {
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
            t_ = StringList.concat(t_, ch_.get(i).getAnalyzedType(),getSeparator(i));
        }
        t_ = StringList.concat(t_, ch_.last().getAnalyzedType(),getEnd());
        setAnalyzedType(t_);
    }

    boolean okTmp(ContextEl _an, StringMap<StringList> _inherit) {
        AnaPartType f_ = getFirstChild();
        String tempCl_ = f_.getAnalyzedType();
        String tempClFull_ = fetchTemplate();
        tempCl_ = StringExpUtil.getIdFromAllTypes(tempCl_);
        AnaGeneType type_ = _an.getAnalyzing().getAnaGeneType(_an,tempCl_);
        CustList<StringList> boundsAll_ = type_.getBoundAll();
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
            if (StringList.quickEq(arg_, Templates.SUB_TYPE)) {
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
                String param_ = AnaTemplates.format(type_,tempClFull_, e, _an);
                m_.setParam(param_);
                boolean ok_ = false;
                for (String v: bounds_) {
                    m_.setArg(v);
                    m_.setMapping(_inherit);
                    if (AnaTemplates.isCorrect(m_, _an)) {
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

    void buildBadConstraintsOffsetList(ContextEl _an) {
        if (!_an.isGettingParts()) {
            return;
        }
        for (int i: indexesChildConstraints) {
            String err_ = FoundErrorInterpret.buildARError(_an.getAnalysisMessages().getBadParamerizedType(), getAnalyzedType());
            getErrsList().get(i).add(err_);
        }
        for (int i: indexesChildConstraints) {
            int begin_ = _an.getAnalyzing().getLocalInType() + getIndexInType() + getOperators().getKey(i);
            int len_ = getOperators().getValue(i).length();
            String err_ = LinkageUtil.transform(StringList.join(getErrsList().get(i),"\n\n"));
            getBeginOps().set(i,new PartOffset("<a title=\""+err_+"\" class=\"e\">",begin_));
            getEndOps().set(i,new PartOffset("</a>",begin_+len_));
        }
    }

    void processBadLen(ContextEl _an) {
        int begin_ = _an.getAnalyzing().getLocalInType() + getIndexInType() + getOperators().lastKey();
        int len_ = getOperators().lastValue().length();
        StringList errLen_ = getErrs();
        if (errLen_.isEmpty()) {
            return;
        }
        String err_ = LinkageUtil.transform(StringList.join(errLen_,"\n\n"));
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
            t_ = StringList.concat(t_, ch_.get(i).getAnalyzedType(),getSeparator(i));
        }
        return StringList.concat(t_, ch_.last().getAnalyzedType(),getEnd());
    }

    @Override
    void buildErrorInexist(ContextEl _an) {
        int begin_ = _an.getAnalyzing().getLocalInType() + getIndexInType() + getOperators().firstKey();
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
