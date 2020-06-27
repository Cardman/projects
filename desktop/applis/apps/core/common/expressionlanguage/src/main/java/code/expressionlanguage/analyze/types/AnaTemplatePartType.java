package code.expressionlanguage.analyze.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.AccessedBlock;
import code.expressionlanguage.analyze.inherits.Mapping;
import code.expressionlanguage.inherits.Templates;

import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;
import code.util.StringMap;

final class AnaTemplatePartType extends AnaBinaryType {
    AnaTemplatePartType(AnaParentPartType _parent, int _index, int _indexInType) {
        super(_parent, _index, _indexInType);
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
        CustList<AnaPartType> ch_ = new CustList<AnaPartType>();
        AnaPartType f_ = getFirstChild();
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
    void analyzeAccessibleId(ContextEl _an, CustList<IntTreeMap<String>> _dels, AccessedBlock _rooted) {
        CustList<AnaPartType> ch_ = new CustList<AnaPartType>();
        AnaPartType f_ = getFirstChild();
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

    boolean okTmp(ContextEl _an, StringMap<StringList> _inherit) {
        AnaPartType f_ = getFirstChild();
        String tempCl_ = f_.getAnalyzedType();
        String tempClFull_ = fetchTemplate();
        tempCl_ = StringExpUtil.getIdFromAllTypes(tempCl_);
        GeneType type_ = _an.getClassBody(tempCl_);
        CustList<StringList> boundsAll_ = type_.getBoundAll();
        for (StringList t: boundsAll_) {
            f_ = f_.getNextSibling();
            String arg_ = f_.getAnalyzedType();
            if (StringList.quickEq(arg_, Templates.SUB_TYPE)) {
                continue;
            }
            String comp_ = arg_;
            if (comp_.startsWith(Templates.SUB_TYPE)) {
                comp_ = comp_.substring(Templates.SUB_TYPE.length());
            } else if (comp_.startsWith(Templates.SUP_TYPE)) {
                comp_ = comp_.substring(Templates.SUP_TYPE.length());
            }
            DimComp dimCompArg_ = StringExpUtil.getQuickComponentBaseType(comp_);
            comp_ = dimCompArg_.getComponent();
            boolean lookInInherit_ = comp_.startsWith(Templates.PREFIX_VAR_TYPE);
            StringList bounds_ = new StringList();
            if (lookInInherit_) {
                bounds_.addAllElts(_inherit.getVal(comp_.substring(1)));
            } else {
                bounds_.add(comp_);
            }
            for (String e: t) {
                Mapping m_ = new Mapping();
                String param_ = Templates.format(tempClFull_, e, _an);
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
                    return false;
                }
            }
        }
        return true;
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
}
