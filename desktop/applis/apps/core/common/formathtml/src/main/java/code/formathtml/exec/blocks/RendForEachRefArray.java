package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArrayWrapper;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;

public final class RendForEachRefArray extends RendAbstractForEachLoop {
    public RendForEachRefArray(String _importedClassName, String _variable, int _expressionOffset, String _classIndex, String _label, CustList<RendDynOperationNode> _res) {
        super(_importedClassName, _variable, _expressionOffset, _classIndex, _label, _res);
    }

    @Override
    protected RendLoopBlockStack newLoopBlockStack(Configuration _conf, BeanLgNames _stds, ContextEl _cont, String _label, Struct _its, RendStackCall _rendStack) {
        return newLoopBlockStackArr(_cont, _label, _its, _rendStack, this);
    }

    @Override
    protected void putVar(ContextEl _ctx, RendStackCall _rendStack, RendLoopBlockStack _l) {
        ImportingPage ip_ = _rendStack.getLastPage();
        ip_.getRefParams().put(getVariableName(), new ArrayWrapper(_l.getContent().getContainer(),new LongStruct(0)));
    }
    @Override
    protected Argument retrieveValue(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        Struct container_ = _l.getContent().getContainer();
        LongStruct lg_ = new LongStruct(_l.getContent().getIndex());
        ip_.getRefParams().set(getVariableName(),new ArrayWrapper(container_,lg_));
        return new Argument(ExecTemplates.getElement(container_, lg_, _ctx, _rendStack.getStackCall()));
    }

    @Override
    protected ConditionReturn hasNext(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l, RendStackCall _rendStack) {
        return hasNext(_l);
    }
}
