package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.maths.litteralcom.StrTypes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

final class ExecTemplatePartType extends ExecBinaryType {

    ExecTemplatePartType(ExecParentPartType _parent, int _index, String _previousOperator, StrTypes _valuesEx) {
        super(_parent, _index, _previousOperator,_valuesEx);
    }

    @Override
    String getPrettyBegin() {
        return EMPTY_STRING;
    }
    @Override
    String getBegin() {
        return EMPTY_STRING;
    }

    private static String getSeparator(int _index) {
        if (_index == 0) {
            return StringExpUtil.TEMPLATE_BEGIN;
        }
        return StringExpUtil.TEMPLATE_SEP;
    }

    @Override
    String getPrettyEnd() {
        return StringExpUtil.TEMPLATE_END;
    }
    @Override
    String getEnd() {
        return StringExpUtil.TEMPLATE_END;
    }

    @Override
    boolean analyzeTree(ContextEl _an) {
        String tempClFull_ = fetchTemplate();
        setAnalyzedType(tempClFull_);
        return true;
    }

    boolean okTmp(ContextEl _an) {
        CustList<ExecPartType> ch_ = getChildren();
        String tempCl_ = ch_.first().getAnalyzedType();
        String tempClFull_ = fetchTemplate();
        tempCl_ = StringExpUtil.getIdFromAllTypes(tempCl_);
        GeneType type_ = _an.getClassBody(tempCl_);
        CustList<StringList> boundsAll_ = type_.getBoundAll();
        int len_ = Math.min(ch_.size()-1,boundsAll_.size());
        for (int i = 0; i < len_; i++) {
            StringList t_ = boundsAll_.get(i);
            String arg_ = ch_.get(i+1).getAnalyzedType();
            if (StringUtil.quickEq(arg_, StringExpUtil.SUB_TYPE) || arg_.startsWith(StringExpUtil.SUB_TYPE) || arg_.startsWith(StringExpUtil.SUP_TYPE)) {
                continue;
            }
            String comp_ = arg_;
            DimComp dimCompArg_ = StringExpUtil.getQuickComponentBaseType(comp_);
            comp_ = dimCompArg_.getComponent();
            for (String e: t_) {
                String param_ = ExecInherits.format(tempClFull_, e, _an);
                if (!ExecInherits.isCorrectExecute(comp_, param_, _an)) {
                    return false;
                }
            }
        }
        return true;
    }

    private String fetchTemplate() {
        CustList<ExecPartType> ch_ = getChildren();
        String t_ = getBegin();
        int len_ = ch_.size() - 1;
        for (int i = 0; i < len_; i++) {
            t_ = StringUtil.concat(t_, ch_.get(i).getAnalyzedType(),getSeparator(i));
        }
        return StringUtil.concat(t_, ch_.last().getAnalyzedType(),getEnd());
    }
}
