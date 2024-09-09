package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

public final class ExecAbstractCaseCondition extends ExecBracedBlock implements
        WithEl,ExecWithFilterContent {

    private final ExecOperationNodeListOff exp;
    private final ExecFilterContent content;

    public ExecAbstractCaseCondition(int _b, ExecOperationNodeListOff _ex, String _c, String _v, CustList<Struct> _stdValues, CustList<ClassField> _enumValues) {
        exp = _ex;
        content = new ExecFilterContent(_b,_c, _v, _stdValues, _enumValues);
    }
    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.setVisitedCase(_cont,_stack, this);
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        if (!content.getVariableName().isEmpty()) {
            _ip.removeRefVar(content.getVariableName());
        }
    }
    public ExecOperationNodeListOff getExp() {
        return exp;
    }

    public ExecFilterContent getContent() {
        return content;
    }
}
