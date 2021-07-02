package code.formathtml.exec.blocks;

import code.formathtml.ImportingPage;
import code.util.StringList;

public abstract class RendAbstractDeclareVariable extends RendLeaf {

    private final StringList variableNames;

    protected RendAbstractDeclareVariable(StringList _variableNames) {
        variableNames = _variableNames;
    }
    public abstract void removeLocalVars(ImportingPage _ip);
    public StringList getVariableNames() {
        return variableNames;
    }
}
