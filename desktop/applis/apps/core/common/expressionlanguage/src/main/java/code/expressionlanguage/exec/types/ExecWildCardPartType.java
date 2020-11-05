package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.util.core.StringUtil;

final class ExecWildCardPartType extends ExecParentPartType {
    private String prefix;
    ExecWildCardPartType(ExecParentPartType _parent, int _index, String _prefix, String _previousOperator) {
        super(_parent, _index, _previousOperator);
        prefix = _prefix;
    }

    @Override
    String getBegin() {
        return prefix;
    }

    @Override
    String getPrettyBegin() {
        return prefix;
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
    boolean analyzeTree(ContextEl _an) {
        if (!(getParent() instanceof ExecTemplatePartType)) {
            return false;
        }
        ExecPartType prev_ = getParent().getChildren().first();
        String base_ = ((ExecNamePartType)prev_).getTypeName();
        if (StringUtil.quickEq(base_.trim(), _an.getStandards().getContent().getReflect().getAliasFct())) {
            return false;
        }
        String ch_ = getChildren().first().getAnalyzedType();
        ch_ = StringUtil.concat(getBegin(),ch_);
        setAnalyzedType(ch_);
        return true;
    }
}
