package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.inherits.ExecArrayTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendForEachArray extends RendAbstractForEachLoop {
    public RendForEachArray(String _importedClassName, String _variable, int _expressionOffset, String _classIndex, String _label, CustList<RendDynOperationNode> _res) {
        super(_importedClassName, _variable, _expressionOffset, _classIndex, _label, _res);
    }

    @Override
    protected RendLoopBlockStack newLoopBlockStack(Configuration _conf, BeanLgNames _stds, ContextEl _cont, String _label, Struct _its, RendStackCall _rendStack) {
        return newLoopBlockStackArr(_cont, _label, _its, _rendStack, this);
    }

    @Override
    protected void putVar(ContextEl _ctx, RendStackCall _rendStack, RendLoopBlockStack _l) {
        ImportingPage ip_ = _rendStack.getLastPage();
        String importedClassName_ = _rendStack.formatVarType(getImportedClassName());
        Struct struct_ = ExecClassArgumentMatching.defaultValue(importedClassName_, _ctx);
        ip_.putValueVar(getVariableName(), new VariableWrapper(LocalVariable.newLocalVariable(struct_, importedClassName_)));
    }

    @Override
    protected Argument retrieveValue(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l, RendStackCall _rendStack) {
        Struct container_ = _l.getContent().getContainer();
        LongStruct lg_ = new LongStruct(_l.getContent().getIndex());
        return new Argument(ExecArrayTemplates.getElement(container_, lg_, _ctx, _rendStack.getStackCall()));
    }

    @Override
    protected ConditionReturn hasNext(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l, RendStackCall _rendStack) {
        return hasNext(_l);
    }

}
