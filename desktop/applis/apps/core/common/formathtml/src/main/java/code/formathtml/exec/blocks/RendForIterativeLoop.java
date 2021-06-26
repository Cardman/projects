package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.util.StringMap;

public abstract class RendForIterativeLoop extends RendParentBlock implements RendLoop {

    private final String label;

    private final String importedClassName;
    private final String importedClassIndexName;

    private final String variableName;

    private final RendOperationNodeListOff init;
    private final RendOperationNodeListOff exp;
    private final RendOperationNodeListOff step;

    protected RendForIterativeLoop(String _className, String _variable,
                                   String _classIndex, String _label,
                                   RendOperationNodeListOff _init, RendOperationNodeListOff _exp, RendOperationNodeListOff _step) {
        importedClassName = _className;
        variableName = _variable;
        importedClassIndexName = _classIndex;
        label = _label;
        init = _init;
        exp = _exp;
        step = _step;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        processLoop(_cont, _stds, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        c_ = (RendLoopBlockStack) ip_.getRendLastStack();
        if (c_.isFinished()) {
            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        ip_.getRendReadWrite().setRead(getFirstChild());
    }

    private void processLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        LgNames stds_ = _ctx.getStandards();
        String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
        ImportingPage ip_ = _rendStackCall.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();

        ip_.setOffset(init.getOffset());
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrFrom());
        Argument argFrom_ = RenderExpUtil.calculateReuse(init.getList(), _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        if (argFrom_.isNull()) {
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, null_, _rendStackCall.getStackCall())));
            return;
        }
        ip_.setOffset(exp.getOffset());
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrTo());
        Argument argTo_ = RenderExpUtil.calculateReuse(exp.getList(), _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        if (argTo_.isNull()) {
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, null_, _rendStackCall.getStackCall())));
            return;
        }
        ip_.setOffset(step.getOffset());
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrStep());
        Argument argStep_ = RenderExpUtil.calculateReuse(step.getList(), _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        if (argStep_.isNull()) {
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, null_, _rendStackCall.getStackCall())));
            return;
        }
        long fromValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argFrom_.getStruct())).longStruct();
        long toValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argTo_.getStruct())).longStruct();
        long stepValue_ = ExecHelperBlocks.stepValue(argStep_,fromValue_,toValue_);
        boolean eq_ = this instanceof RendForIterativeLoopEq;
        boolean finished_ = stepValue_ == 0 || fromValue_ == toValue_ && !eq_;
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLoop(this);
        l_.setLabel(label);
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setEq(eq_);
        l_.setCurrentValue(fromValue_);
        l_.setAchieveValue(toValue_);
        l_.setStep(stepValue_);
        ip_.addBlock(l_);
        if (finished_) {
            return;
        }
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndexClassName(importedClassIndexName);
        Struct struct_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _ctx.getStandards().getPrimTypes()), new LongStruct(fromValue_));
        varsLoop_.put(var_, lv_);
        ip_.putValueVar(var_, LocalVariable.newLocalVariable(struct_,importedClassName));
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
        _ip.removeRefVar(var_);
    }

    @Override
    public void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        RendBlock forLoopLoc_ = l_.getBlock();
        if (l_.hasNextIter()) {
            incrementLoop(l_, _ctx, _rendStack);
            rw_.setRead(forLoopLoc_.getFirstChild());
            return;
        }
        l_.setFinished(true);
    }

    public void incrementLoop(RendLoopBlockStack _l,
                              ContextEl _ctx, RendStackCall _stackCall) {
        _l.setIndex(_l.getIndex() + 1);
        _l.incr();
        String var_ = getVariableName();
        Argument struct_ = ExecTemplates.getWrapValue(_ctx,var_, -1, _stackCall.getLastPage().getPageEl().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall.getStackCall());
        long o_ = NumParsers.convertToNumber(struct_.getStruct()).longStruct()+_l.getStep();
        Struct element_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _ctx.getStandards().getPrimTypes()), new LongStruct(o_));
        ExecTemplates.setWrapValue(_ctx,var_, new Argument(element_),-1, _stackCall.getLastPage().getPageEl().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall.getStackCall());
        ExecTemplates.incrIndexLoop(_ctx,var_, -1, _stackCall.getLastPage().getPageEl().getCache(), _stackCall.getLastPage().getVars(), _stackCall.getStackCall());
    }
}
