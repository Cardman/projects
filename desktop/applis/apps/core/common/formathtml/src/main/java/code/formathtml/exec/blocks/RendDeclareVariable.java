package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.StringList;

public final class RendDeclareVariable extends RendAbstractDeclareVariable {


    private final String importedClassName;

    public RendDeclareVariable(String _info, StringList _variables) {
        super(_variables);
        importedClassName = _info;
    }

    @Override
    public void removeLocalVars(ImportingPage _ip) {
        for (String v: getVariableNames()) {
            _ip.removeRefVar(v);
        }
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        Struct struct_ = ExecClassArgumentMatching.defaultValue(importedClassName, _ctx);
        for (String v: getVariableNames()) {
            LocalVariable lv_ = LocalVariable.newLocalVariable(struct_,importedClassName);
            ip_.putValueVar(v, new VariableWrapper(lv_));
        }
        processBlock(_cont, _stds, _ctx, _rendStack);
    }

}
