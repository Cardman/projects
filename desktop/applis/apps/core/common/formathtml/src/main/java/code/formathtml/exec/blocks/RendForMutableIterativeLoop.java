package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.util.StringList;

public final class RendForMutableIterativeLoop extends RendParentBlock implements RendLoop,RendWithEl {


    private final String label;

    private final String importedClassName;

    private final String importedClassIndexName;

    private final StringList variableNames;

    private final RendOperationNodeListOff init;
    private final RendOperationNodeListOff exp;
    private final RendOperationNodeListOff step;

    public RendForMutableIterativeLoop(String _className,
                                       StringList _varNames, String _classIndex, String _label,
                                       RendOperationNodeListOff _init, RendOperationNodeListOff _exp, RendOperationNodeListOff _step) {
        importedClassName = _className;
        variableNames = _varNames;
        importedClassIndexName = _classIndex;
        label = _label;
        init = _init;
        exp = _exp;
        step = _step;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        ip_.setOffset(init.getOffset());
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrInit());
        Struct struct_ = ExecClassArgumentMatching.defaultValue(importedClassName, _ctx);
        for (String v: variableNames) {
            LoopVariable lv_ = new LoopVariable();
            lv_.setIndexClassName(importedClassIndexName);
            ip_.getVars().put(v, lv_);
            ip_.putValueVar(v, LocalVariable.newLocalVariable(struct_,importedClassName));
        }
        if (!init.getList().isEmpty()) {
            RenderExpUtil.calculateReuse(init.getList(), _stds, _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
        }
        ConditionReturn res_ = evaluateCondition(_cont, _stds, _ctx, _rendStack);
        if (res_ == ConditionReturn.CALL_EX) {
            return;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLoop(this);
        l_.setLabel(label);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setFinished(res_ == ConditionReturn.NO);
        ip_.addBlock(l_);
        c_ = (RendLoopBlockStack) ip_.getRendLastStack();
        if (c_.isFinished()) {
            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        rw_.setRead(getFirstChild());
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        for (String v: variableNames) {
            _ip.getVars().removeKey(v);
        }
        for (String v: variableNames) {
            _ip.removeRefVar(v);
        }
    }

    private ConditionReturn evaluateCondition(Configuration _context, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStackCall) {
        ImportingPage last_ = _rendStackCall.getLastPage();
        if (exp.getList().isEmpty()) {
            return ConditionReturn.YES;
        }
        last_.setOffset(exp.getOffset());
        last_.setProcessingAttribute(_context.getRendKeyWords().getAttrCondition());
        Argument arg_ = RenderExpUtil.calculateReuse(exp.getList(), _stds, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    @Override
    public void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        RendBlock forLoopLoc_ = l_.getBlock();
        ip_.setOffset(step.getOffset());
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrStep());
        if (!step.getList().isEmpty()) {
            RenderExpUtil.calculateReuse(step.getList(), _advStandards, _ctx, _rendStack);
            if (_ctx.callsOrException(_rendStack.getStackCall())) {
                return;
            }
        }
        for (String v: variableNames) {
            LoopVariable lv_ = ip_.getVars().getVal(v);
            lv_.setIndex(lv_.getIndex()+1);
        }
        ConditionReturn keep_ = evaluateCondition(_conf, _advStandards, _ctx, _rendStack);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            l_.setFinished(true);
        } else {
            rw_.setRead(forLoopLoc_.getFirstChild());
        }
    }
}
