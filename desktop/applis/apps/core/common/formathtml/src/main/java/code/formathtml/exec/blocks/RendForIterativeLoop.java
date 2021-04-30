package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.variables.AbstractWrapper;
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
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringMap;

public final class RendForIterativeLoop extends RendParentBlock implements RendLoop {

    private final String label;

    private final String importedClassName;
    private final String importedClassIndexName;

    private final String variableName;

    private final int initOffset;

    private final int expressionOffset;

    private final int stepOffset;

    private final boolean eq;
    private final CustList<RendDynOperationNode> opInit;

    private final CustList<RendDynOperationNode> opExp;

    private final CustList<RendDynOperationNode> opStep;

    public RendForIterativeLoop(String _className, String _variable,
                         int _from,
                         int _to, boolean _eq, int _step, String _classIndex, String _label, int _offsetTrim,
                         CustList<RendDynOperationNode> _opInit, CustList<RendDynOperationNode> _opExp, CustList<RendDynOperationNode> _opStep) {
        super(_offsetTrim);
        importedClassName = _className;
        variableName = _variable;
        initOffset = _from;
        expressionOffset = _to;
        stepOffset = _step;
        eq = _eq;
        importedClassIndexName = _classIndex;
        label = _label;
        opInit = _opInit;
        opExp = _opExp;
        opStep = _opStep;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, StackCall _stack, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            processBlockAndRemove(_cont, _stds, _ctx, _stack, _rendStack);
            return;
        }
        processLoop(_cont, _stds, _ctx, _stack, _rendStack);
        if (_ctx.callsOrException(_stack)) {
            return;
        }
        c_ = (RendLoopBlockStack) ip_.getRendLastStack();
        if (c_.isFinished()) {
            processBlockAndRemove(_cont, _stds, _ctx, _stack, _rendStack);
            return;
        }
        ip_.getRendReadWrite().setRead(getFirstChild());
    }

    private void processLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, StackCall _stackCall, RendStackCall _rendStackCall) {
        LgNames stds_ = _ctx.getStandards();
        String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
        ImportingPage ip_ = _rendStackCall.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();

        ip_.setOffset(initOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrFrom());
        Argument argFrom_ = RenderExpUtil.calculateReuse(opInit,_conf, _advStandards, _ctx, _stackCall, _rendStackCall);
        if (_ctx.callsOrException(_stackCall)) {
            return;
        }
        if (argFrom_.isNull()) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, null_, _stackCall)));
            return;
        }
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrTo());
        Argument argTo_ = RenderExpUtil.calculateReuse(opExp,_conf, _advStandards, _ctx, _stackCall, _rendStackCall);
        if (_ctx.callsOrException(_stackCall)) {
            return;
        }
        if (argTo_.isNull()) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, null_, _stackCall)));
            return;
        }
        ip_.setOffset(stepOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrStep());
        Argument argStep_ = RenderExpUtil.calculateReuse(opStep,_conf, _advStandards, _ctx, _stackCall, _rendStackCall);
        if (_ctx.callsOrException(_stackCall)) {
            return;
        }
        if (argStep_.isNull()) {
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, null_, _stackCall)));
            return;
        }
        long fromValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argFrom_.getStruct())).longStruct();
        long toValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argTo_.getStruct())).longStruct();
        long stepValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argStep_.getStruct())).longStruct();
        if (stepValue_ > 0) {
            if (fromValue_ > toValue_) {
                stepValue_ = -stepValue_;
            }
        } else if (stepValue_ < 0) {
            if (fromValue_ < toValue_) {
                stepValue_ = -stepValue_;
            }
        }
        boolean finished_ = stepValue_ == 0 || fromValue_ == toValue_ && !eq;
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLoop(this);
        l_.setLabel(label);
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setEq(eq);
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
    public void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock, StackCall _stack, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<AbstractWrapper> varsInfos_ = ip_.getRefParams();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        RendBlock forLoopLoc_ = l_.getBlock();
        if (l_.hasNextIter()) {
            incrementLoop(l_, vars_,varsInfos_, _ctx, _stack);
            rw_.setRead(forLoopLoc_.getFirstChild());
            return;
        }
        l_.setFinished(true);
    }

    public void incrementLoop(RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars, StringMap<AbstractWrapper> _varsInfos, ContextEl _ctx, StackCall _stackCall) {
        _l.setIndex(_l.getIndex() + 1);
        _l.incr();
        String var_ = getVariableName();
        LoopVariable lv_ = _vars.getVal(var_);
        AbstractWrapper lInfo_ = _varsInfos.getVal(var_);
        long o_ = NumParsers.convertToNumber(lInfo_.getValue(_stackCall, _ctx)).longStruct()+_l.getStep();
        lInfo_.setValue(_stackCall, _ctx,new Argument(NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _ctx.getStandards().getPrimTypes()), new LongStruct(o_))));
        lv_.setIndex(lv_.getIndex() + 1);
    }
}
