package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.util.StringList;

public abstract class ExecAbstractDeclareVariable extends ExecLeaf implements WithEl {

    private final StringList variableNames;
    protected ExecAbstractDeclareVariable(StringList _variableNames) {
        variableNames = _variableNames;
    }

    public abstract void removeLocalVars(AbstractPageEl _ip);
    public StringList getVariableNames() {
        return variableNames;
    }
}
