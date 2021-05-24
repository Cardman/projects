package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecForEachArray;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArrayWrapper;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class RendForEachRefArray extends RendAbstractForEachLoop {
    public RendForEachRefArray(String _importedClassName, String _variable, int _expressionOffset, String _classIndex, String _label, int _offsetTrim, CustList<RendDynOperationNode> _res) {
        super(_importedClassName, _variable, _expressionOffset, _classIndex, _label, _offsetTrim, _res);
    }

    @Override
    protected RendLoopBlockStack newLoopBlockStack(Configuration _conf, BeanLgNames _stds, ContextEl _cont, String _label, Struct _its, RendStackCall _rendStack) {
        boolean finished_ = false;
        long length_ = ExecForEachArray.getLength(_its, _cont, _rendStack.getStackCall());
        if (length_ == IndexConstants.SIZE_EMPTY) {
            finished_ = true;
        }
        if (_cont.callsOrException(_rendStack.getStackCall())) {
            return null;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLabel(_label);
        l_.setLoop(this);
        l_.setIndex(-1);
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setMaxIteration(length_);
        l_.setContainer(_its);
        return l_;
    }

    @Override
    protected void putVar(ContextEl _ctx, RendStackCall _rendStack, RendLoopBlockStack _l) {
        ImportingPage ip_ = _rendStack.getLastPage();
        ip_.getRefParams().put(getVariableName(), new ArrayWrapper(_l.getContainer(),new LongStruct(0)));
    }
    @Override
    protected Argument retrieveValue(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        Struct container_ = _l.getContainer();
        LongStruct lg_ = new LongStruct(_l.getIndex());
        ip_.getRefParams().set(getVariableName(),new ArrayWrapper(container_,lg_));
        return new Argument(ExecTemplates.getElement(container_, lg_, _ctx, _rendStack.getStackCall()));
    }

    @Override
    protected ConditionReturn hasNext(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l, RendStackCall _rendStack) {
        if (_l.hasNext()) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
}
