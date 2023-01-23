package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.blocks.ExecSwitchValuesList;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.util.CustList;

public final class RendListCatchEval extends RendAbstractCatchEval {
    private final ExecSwitchValuesList list;
    public RendListCatchEval(CustList<Argument> _stdValues, CustList<ClassField> _enumValues) {
        super(new CustList<RendDynOperationNode>(),0);
        list = new ExecSwitchValuesList(_stdValues, _enumValues);
    }

    public ExecSwitchValuesList getList() {
        return list;
    }
}
