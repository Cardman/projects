package code.expressionlanguage.exec.types;

import code.expressionlanguage.ContextEl;
import code.util.CustList;
import code.util.IntTreeMap;

final class ExecEmptyPartType extends ExecLeafPartType {
    ExecEmptyPartType(ExecParentPartType _parent, int _index, String _type, String _previousSeparator) {
        super(_parent, _index, _type, _previousSeparator);
    }

    @Override
    void checkDynExistence(ContextEl _an, CustList<IntTreeMap<String>> _dels) {
    }

}
