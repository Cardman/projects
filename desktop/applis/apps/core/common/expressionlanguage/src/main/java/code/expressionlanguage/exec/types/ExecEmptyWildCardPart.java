package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.Templates;
import code.util.CustList;
import code.util.IntTreeMap;

final class ExecEmptyWildCardPart extends ExecLeafPartType {
    ExecEmptyWildCardPart(ExecParentPartType _parent, int _index, String _type, String _previousSeparator) {
        super(_parent, _index, _type, _previousSeparator);
    }

    @Override
    void checkDynExistence(ContextEl _an, CustList<IntTreeMap<String>> _dels) {
        if (!(getParent() instanceof ExecTemplatePartType)) {
            return;
        }
        setImportedTypeName(Templates.SUB_TYPE);
        setAnalyzedType(Templates.SUB_TYPE);
    }

}
