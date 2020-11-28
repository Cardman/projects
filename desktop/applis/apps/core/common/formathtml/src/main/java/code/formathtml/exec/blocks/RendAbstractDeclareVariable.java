package code.formathtml.exec.blocks;

import code.formathtml.ImportingPage;
import code.util.StringList;

public abstract class RendAbstractDeclareVariable extends RendLeaf implements RendWithEl {

    private StringList variableNames;

    protected RendAbstractDeclareVariable(int _offsetTrim, StringList _variableNames) {
        super(_offsetTrim);
        variableNames = _variableNames;
    }
    public abstract void removeLocalVars(ImportingPage _ip);
    public StringList getVariableNames() {
        return variableNames;
    }
}
