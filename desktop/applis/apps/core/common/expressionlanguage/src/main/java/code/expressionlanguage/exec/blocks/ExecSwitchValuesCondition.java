package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecSwitchValuesCondition extends ExecAbstractCaseCondition {
    private final ExecSwitchValuesList list;
    public ExecSwitchValuesCondition(CustList<Argument> _stdValues, CustList<ClassField> _enumValues, CustList<ExecOperationNode> _list, int _offset) {
        super(_list, _offset);
        list = new ExecSwitchValuesList(_stdValues, _enumValues);
    }

    public ExecSwitchValuesList getList() {
        return list;
    }
}
