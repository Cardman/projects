package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassArgumentMatching;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.exec.variables.VariableWrapper;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.stacks.DefRendReadWrite;
import code.formathtml.exec.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.StringMap;

public abstract class RendForIterativeLoop extends RendParentBlock implements RendWithEl {

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
            processVisitedLoop(_cont,_stds,c_,this,_ctx,_rendStack);
//            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        RendLoopBlockStack l_ = processLoop(_ctx, _rendStack);
        if (l_ == null) {
            return;
        }
        visitOrFinish(_cont,_stds,_ctx,_rendStack,this,ip_,l_);
    }

    private RendLoopBlockStack processLoop(ContextEl _ctx, RendStackCall _rendStackCall) {
        LgNames stds_ = _ctx.getStandards();
        String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
        ImportingPage ip_ = _rendStackCall.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();

        ip_.setOffset(init.getOffset());
        Argument argFrom_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(init.getList(), _ctx, _rendStackCall).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return null;
        }
        if (argFrom_.isNull()) {
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, null_, _rendStackCall.getStackCall())));
            return null;
        }
        ip_.setOffset(exp.getOffset());
        Argument argTo_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(exp.getList(), _ctx, _rendStackCall).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return null;
        }
        if (argTo_.isNull()) {
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, null_, _rendStackCall.getStackCall())));
            return null;
        }
        ip_.setOffset(step.getOffset());
        Argument argStep_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(step.getList(), _ctx, _rendStackCall).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return null;
        }
        if (argStep_.isNull()) {
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, null_, _rendStackCall.getStackCall())));
            return null;
        }
        long fromValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argFrom_.getStruct())).longStruct();
        long toValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argTo_.getStruct())).longStruct();
        long stepValue_ = ExecHelperBlocks.stepValue(argStep_,fromValue_,toValue_);
        boolean eq_ = this instanceof RendForIterativeLoopEq;
        boolean finished_ = stepValue_ == 0 || fromValue_ == toValue_ && !eq_;
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLabel(label);
        l_.getContent().setFinished(finished_);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.getContent().setEq(eq_);
        l_.getContent().setCurrentValue(fromValue_);
        l_.getContent().setAchieveValue(toValue_);
        l_.getContent().setStep(stepValue_);
        ip_.addBlock(l_);
        if (finished_) {
            return l_;
        }
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndexClassName(importedClassIndexName);
        Struct struct_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _ctx.getStandards().getPrimTypes()), new LongStruct(fromValue_));
        varsLoop_.put(var_, lv_);
        ip_.putValueVar(var_, new VariableWrapper(LocalVariable.newLocalVariable(struct_,importedClassName)));
        return l_;
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
        _ip.removeRefVar(var_);
    }

    public void processLastElementLoop(ContextEl _ctx, RendLoopBlockStack _loopBlock, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        DefRendReadWrite rw_ = ip_.getRendReadWrite();
        RendBlock forLoopLoc_ = _loopBlock.getCurrentVisitedBlock();
        if (_loopBlock.getContent().hasNextIter()) {
            incrementLoop(_loopBlock, _ctx, _rendStack);
            rw_.setRead(forLoopLoc_.getFirstChild());
            return;
        }
        _loopBlock.getContent().setFinished(true);
    }

    public void incrementLoop(RendLoopBlockStack _l,
                              ContextEl _ctx, RendStackCall _stackCall) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        _l.getContent().incr();
        String var_ = getVariableName();
        Argument struct_ = ExecVariableTemplates.getWrapValue(_ctx,var_, -1, _stackCall.getLastPage().getPageEl().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall.getStackCall());
        long o_ = NumParsers.convertToNumber(struct_.getStruct()).longStruct()+_l.getContent().getStep();
        Struct element_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _ctx.getStandards().getPrimTypes()), new LongStruct(o_));
        ExecVariableTemplates.setWrapValue(_ctx,var_, new Argument(element_),-1, _stackCall.getLastPage().getPageEl().getCache(), _stackCall.getLastPage().getRefParams(), _stackCall.getStackCall());
        ExecVariableTemplates.incrIndexLoop(_ctx,var_, -1, _stackCall.getLastPage().getPageEl().getCache(), _stackCall.getLastPage().getVars(), _stackCall.getStackCall());
    }
}
