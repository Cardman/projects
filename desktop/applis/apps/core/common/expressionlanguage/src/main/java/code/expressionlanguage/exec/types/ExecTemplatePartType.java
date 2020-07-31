package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecTemplates;

import code.expressionlanguage.inherits.Templates;

import code.util.CustList;
import code.util.IntTreeMap;
import code.util.StringList;

final class ExecTemplatePartType extends ExecBinaryType {

    ExecTemplatePartType(ExecParentPartType _parent, int _index) {
        super(_parent, _index);
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
    String getSingleSeparator(int _index) {
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
    boolean analyzeTree(ContextEl _an, CustList<IntTreeMap<String>> _dels) {
        String tempClFull_ = fetchTemplate();
        setAnalyzedType(tempClFull_);
        return true;
    }

    boolean okTmp(ContextEl _an) {
        ExecPartType f_ = getFirstChild();
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
            if (arg_.startsWith(Templates.SUB_TYPE)) {
                continue;
            }
            if (arg_.startsWith(Templates.SUP_TYPE)) {
                continue;
            }
            String comp_ = arg_;
            DimComp dimCompArg_ = StringExpUtil.getQuickComponentBaseType(comp_);
            comp_ = dimCompArg_.getComponent();
            StringList bounds_ = new StringList();
            bounds_.add(comp_);
            for (String e: t) {
                String param_ = ExecTemplates.format(tempClFull_, e, _an);
                if (!ExecTemplates.isCorrectExecute(comp_, param_, _an)) {
                    return false;
                }
            }
        }
        return true;
    }

    private String fetchTemplate() {
        ExecPartType f_ = getFirstChild();
        CustList<ExecPartType> ch_ = new CustList<ExecPartType>();
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
