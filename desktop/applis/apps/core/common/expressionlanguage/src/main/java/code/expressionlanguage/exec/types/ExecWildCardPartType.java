package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.maths.litteralcom.StrTypes;
import code.util.core.StringUtil;

final class ExecWildCardPartType extends ExecPrefPartType {
    ExecWildCardPartType(ExecParentPartType _parent, int _index, String _prefix, String _previousOperator, StrTypes _valuesEx) {
        super(_parent, _index, _prefix, _previousOperator,_valuesEx);
    }

    @Override
    boolean analyzeTree(ExecTemplatePartType _parent, ContextEl _an) {
        String base_ = ((ExecNamePartType)_parent.getChildren().first()).getTypeName();
        if (StringUtil.quickEq(base_.trim(), _an.getStandards().getContent().getReflect().getAliasFct())) {
            return false;
        }
        String ch_ = getChildren().first().getAnalyzedType();
        ch_ = StringUtil.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
        return true;
    }
}
