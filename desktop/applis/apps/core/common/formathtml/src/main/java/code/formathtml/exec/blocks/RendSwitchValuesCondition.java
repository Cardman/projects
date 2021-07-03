package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecSwitchValuesList;
import code.util.CustList;

public final class RendSwitchValuesCondition extends RendAbstractCaseCondition {
    private final ExecSwitchValuesList list;
    public RendSwitchValuesCondition(CustList<Argument> _stdValues, CustList<ClassField> _enumValues) {
        list = new ExecSwitchValuesList(_stdValues, _enumValues);
    }

    public ExecSwitchValuesList getList() {
        return list;
    }
}
