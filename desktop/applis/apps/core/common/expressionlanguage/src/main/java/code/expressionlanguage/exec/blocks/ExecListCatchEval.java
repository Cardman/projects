package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public final class ExecListCatchEval extends ExecAbstractCatchEval implements WithEl {
    private final ExecSwitchValuesList list;

    public ExecListCatchEval(CustList<Argument> _stdValues, CustList<ClassField> _enumValues, CustList<ExecOperationNode> _ls, int _offset) {
        super(new ExecOperationNodeListOff(_ls, _offset));
        list = new ExecSwitchValuesList(_stdValues, _enumValues);
    }

    public ExecSwitchValuesList getList() {
        return list;
    }

}
