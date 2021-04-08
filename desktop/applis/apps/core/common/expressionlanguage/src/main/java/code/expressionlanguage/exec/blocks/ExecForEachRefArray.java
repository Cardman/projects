package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.ExpressionLanguage;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.LoopBlockStack;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArrayWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ExecForEachRefArray extends ExecAbstractForEachLoop {
    public ExecForEachRefArray(String _label, String _importedClassName, String _importedClassIndexName, String _variableName, int _variableNameOffset, int _expressionOffset, CustList<ExecOperationNode> _opList, int _offsetTrim) {
        super(_label, _importedClassName, _importedClassIndexName, _variableName, _variableNameOffset, _expressionOffset, _opList, _offsetTrim);
    }

    @Override
    protected void checkIfNext(ContextEl _cont, LoopBlockStack _l, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.getRefParams().put(getVariableName(), new ArrayWrapper(_l.getContainer(),new LongStruct(0)));
        incrOrFinish(_cont, hasNext(_cont,_l, _stack),_l, _stack);
    }

    @Override
    protected LoopBlockStack newLoopBlockStack(ContextEl _cont, String _label, Struct _its, StackCall _stack) {
        boolean finished_ = false;
        int length_ = getLength(_its, _cont, _stack);
        if (length_ == IndexConstants.SIZE_EMPTY) {
            finished_ = true;
        }
        if (_cont.callsOrException(_stack)) {
            return null;
        }
        LoopBlockStack l_ = new LoopBlockStack();
        l_.setLabel(_label);
        l_.setIndex(-1);
        l_.setFinished(finished_);
        l_.setExecLoop(this);
        l_.setCurrentVisitedBlock(this);
        l_.setMaxIteration(length_);
        l_.setContainer(_its);
        return l_;
    }
    private static int getLength(Struct _str, ContextEl _cont, StackCall _stackCall) {
        if (_str instanceof ArrayStruct) {
            return ((ArrayStruct)_str).getLength();
        }
        String npe_ = _cont.getStandards().getContent().getCoreNames().getAliasNullPe();
        _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, npe_, _stackCall)));
        return -1;
    }
    @Override
    protected Argument retrieveValue(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        Struct container_ = _l.getContainer();
        LongStruct lg_ = new LongStruct(_l.getIndex());
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.getRefParams().set(getVariableName(),new ArrayWrapper(container_,lg_));
        return new Argument(ExecTemplates.getElement(container_, lg_, _conf, _stack));
    }

    @Override
    protected ConditionReturn hasNext(ContextEl _conf, LoopBlockStack _l, StackCall _stack) {
        if (_l.hasNext()) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        return getOpList();
    }
}
