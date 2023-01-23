package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;

public final class ExecDefaultCondition extends ExecBracedBlock implements
        WithEl {
    private final String variableName;
    public ExecDefaultCondition(){
        this("");
    }
    public ExecDefaultCondition(String _varName){
        variableName = _varName;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        ExecHelperBlocks.setVisitedDefault(_cont,_stack, this);
    }

    @Override
    public void removeAllVars(AbstractPageEl _ip) {
        super.removeAllVars(_ip);
        if (!variableName.isEmpty()) {
            _ip.removeRefVar(variableName);
        }
    }
    public String getVariableName() {
        return variableName;
    }
}
