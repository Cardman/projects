package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class RendForEachIterable extends RendAbstractForEachLoop {
    public RendForEachIterable(String _importedClassName, String _variable, int _expressionOffset, String _classIndex, String _label, int _offsetTrim, CustList<RendDynOperationNode> _res) {
        super(_importedClassName, _variable, _expressionOffset, _classIndex, _label, _offsetTrim, _res);
    }

    @Override
    protected RendLoopBlockStack newLoopBlockStack(Configuration _conf, BeanLgNames _stds, ContextEl _cont, String _label, Struct _its, StackCall _stack, RendStackCall _rendStack) {
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        if (_its == NullStruct.NULL_VALUE) {
            String npe_ = _cont.getStandards().getContent().getCoreNames().getAliasNullPe();
            _stack.setCallingState(new CustomFoundExc(new ErrorStruct(_cont, npe_, _stack)));
            return null;
        }
        Argument arg_ = iterator(_its,_conf, _stds, _cont, _stack, _rendStack);
        if (_cont.callsOrException(_stack)) {
            return null;
        }
        Struct iterStr_ = arg_.getStruct();
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLabel(_label);
        l_.setLoop(this);
        l_.setIndex(-1);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        l_.setContainer(_its);
        return l_;
    }

    @Override
    protected Argument retrieveValue(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l, StackCall _stack, RendStackCall _rendStack) {
        return next(_l.getStructIterator(),_conf, _advStandards, _ctx, _stack, _rendStack);
    }

    @Override
    protected ConditionReturn hasNext(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l, StackCall _stack, RendStackCall _rendStack) {
        return iteratorHasNext(_conf,_advStandards,_ctx,_l, _stack, _rendStack);
    }

    private static ConditionReturn iteratorHasNext(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _rendLastStack, StackCall _stack, RendStackCall _rendStackCall) {
        Struct strIter_ = _rendLastStack.getStructIterator();
        Argument arg_ = hasNext(strIter_,_conf, _advStandards, _ctx, _stack, _rendStackCall);
        if (_ctx.callsOrException(_stack)) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
}
