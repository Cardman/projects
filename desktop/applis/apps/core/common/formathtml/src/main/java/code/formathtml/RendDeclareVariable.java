package code.formathtml;

import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.util.StringList;

public final class RendDeclareVariable extends RendLeaf implements RendWithEl {

    private StringList variableNames = new StringList();

    private String importedClassName;

    private int classNameOffset;

    public RendDeclareVariable(int _offsetTrim, String _info, int _offset, StringList _variables) {
        super(_offsetTrim);
        importedClassName = _info;
        classNameOffset = _offset;
        variableNames = _variables;
    }

    public StringList getVariableNames() {
        return variableNames;
    }

    @Override
    public void processEl(Configuration _cont) {
        ImportingPage ip_ = _cont.getLastPage();
        Struct struct_ = ExecClassArgumentMatching.defaultValue(importedClassName, _cont.getContext());
        for (String v: getVariableNames()) {
            LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,importedClassName);
            ip_.putLocalVar(v, lv_);
        }
        processBlock(_cont);
    }

}
