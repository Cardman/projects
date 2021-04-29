package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;

final class ExecEmptyWildCardPart extends ExecLeafPartType {
    ExecEmptyWildCardPart(ExecParentPartType _parent, int _index, String _type, String _previousSeparator, String _previousOperator) {
        super(_parent, _index, _type, _previousSeparator, _previousOperator);
    }

    @Override
    void checkDynExistence(ContextEl _an) {
        if (!(getParent() instanceof ExecTemplatePartType)) {
            return;
        }
        setImportedTypeName(StringExpUtil.SUB_TYPE);
        setAnalyzedType(StringExpUtil.SUB_TYPE);
    }

}
