package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.ArrayStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class RendForEachArray extends RendAbstractForEachLoop {
    public RendForEachArray(String _importedClassName, String _variable, int _expressionOffset, String _classIndex, String _label, int _offsetTrim, CustList<RendDynOperationNode> _res) {
        super(_importedClassName, _variable, _expressionOffset, _classIndex, _label, _offsetTrim, _res);
    }

    @Override
    protected RendLoopBlockStack newLoopBlockStack(Configuration _conf, BeanLgNames _stds, ContextEl _cont, String _label, Struct _its) {
        boolean finished_ = false;
        long length_ = getLength(_its, _cont);
        if (length_ == IndexConstants.SIZE_EMPTY) {
            finished_ = true;
        }
        if (_cont.callsOrException()) {
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
    private static int getLength(Struct _str, ContextEl _ctx) {
        if (_str instanceof ArrayStruct) {
            return ((ArrayStruct)_str).getLength();
        }
        String npe_ = _ctx.getStandards().getContent().getCoreNames().getAliasNullPe();
        _ctx.setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, npe_)));
        return -1;
    }
    @Override
    protected Argument retrieveValue(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l) {
        Struct container_ = _l.getContainer();
        LongStruct lg_ = new LongStruct(_l.getIndex());
        return new Argument(ExecTemplates.getElement(container_, lg_, _ctx));
    }

    @Override
    protected ConditionReturn hasNext(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l) {
        if (_l.hasNext()) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
}
