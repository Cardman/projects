package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.Struct;
import code.util.StringList;

public final class ExecDeclareVariable extends ExecAbstractDeclareVariable {

    private String importedClassName;

    public ExecDeclareVariable(String _importedClassName, StringList _variableNames, int _offsetTrim) {
        super(_variableNames,_offsetTrim);
        importedClassName = _importedClassName;
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        String formatted_ = _stack.formatVarType(importedClassName);
        Struct struct_ = ExecClassArgumentMatching.defaultValue(formatted_, _cont);
        for (String v: getVariableNames()) {
            LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,formatted_);
            ip_.putValueVar(v, lv_);
        }
        processBlock(_cont, _stack);
    }

    @Override
    public void removeLocalVars(AbstractPageEl _ip) {
        for (String v: getVariableNames()) {
            _ip.removeRefVar(v);
        }
    }

    public void setImportedClassName(String _importedClassName) {
        this.importedClassName = _importedClassName;
    }
}
