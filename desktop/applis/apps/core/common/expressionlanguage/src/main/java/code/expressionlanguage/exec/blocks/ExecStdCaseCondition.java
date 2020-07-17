package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.files.OffsetsBlock;
import code.util.CustList;

public final class ExecStdCaseCondition extends ExecAbstractCaseCondition {

    private Argument arg;

    public ExecStdCaseCondition(OffsetsBlock _offset, int _valueOffset, Argument _arg) {
        super(_offset,_valueOffset);
        arg = _arg;
    }

    public Argument getArg() {
        return arg;
    }
}
