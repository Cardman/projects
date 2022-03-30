package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecInheritsAdv;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class RendForEachTable extends RendParentBlock implements RendWithEl {

    private final String label;

    private final String importedClassNameFirst;

    private final String importedClassNameSecond;

    private final String importedClassIndexName;

    private final String variableNameFirst;


    private final String variableNameSecond;
    private final RendOperationNodeListOff exp;

    public RendForEachTable(String _className, String _variable,
                            String _classNameSec, String _variableSec,
                            String _classIndex, String _label, RendOperationNodeListOff _exp) {
        importedClassNameFirst = _className;
        variableNameFirst = _variable;
        importedClassNameSecond = _classNameSec;
        variableNameSecond = _variableSec;
        importedClassIndexName = _classIndex;
        label = _label;
        exp = _exp;
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
        Struct its_ = processLoopTable(_ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Argument arg_ = iteratorMultTable(its_, _stds, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        RendLoopBlockStack l_ = addedStack(ip_, its_, length_, arg_, label, this);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        String clFirst_ = _rendStack.formatVarType(importedClassNameFirst);
        Struct defFirst_ = ExecClassArgumentMatching.defaultValue(clFirst_, _ctx);
        lv_.setIndexClassName(importedClassIndexName);
        varsLoop_.put(variableNameFirst, lv_);
        ip_.putValueVar(variableNameFirst, new VariableWrapper(LocalVariable.newLocalVariable(defFirst_,clFirst_)));
        lv_ = new LoopVariable();
        lv_.setIndex(-1);
        String clSecond_ = _rendStack.formatVarType(importedClassNameSecond);
        Struct defSecond_ = ExecClassArgumentMatching.defaultValue(clSecond_, _ctx);
        lv_.setIndexClassName(importedClassIndexName);
        varsLoop_.put(variableNameSecond, lv_);
        ip_.putValueVar(variableNameSecond, new VariableWrapper(LocalVariable.newLocalVariable(defSecond_,clSecond_)));
        processLastElementLoop(_stds, _ctx, l_, _rendStack);
    }

    public static RendLoopBlockStack addedStack(ImportingPage _ip, Struct _its, long _length, Argument _arg, String _label, RendParentBlock _block) {
        Struct iterStr_ = _arg.getStruct();
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLabel(_label);
        l_.getContent().setIndex(-1);
        l_.getContent().setFinished(false);
        l_.setBlock(_block);
        l_.setCurrentVisitedBlock(_block);
        l_.getContent().setStructIterator(iterStr_);
        l_.getContent().setMaxIteration(_length);
        l_.getContent().setContainer(_its);
        _ip.addBlock(l_);
        return l_;
    }

    Struct processLoopTable(ContextEl _ctx, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        ip_.setOffset(exp.getOffset());
        Argument arg_ = Argument.getNullableValue(RenderExpUtil.getAllArgs(exp.getList(), _ctx, _rendStackCall).lastValue().getArgument());
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_ == NullStruct.NULL_VALUE) {
            String npe_ = _ctx.getStandards().getContent().getCoreNames().getAliasNullPe();
            _rendStackCall.getStackCall().setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, npe_, _rendStackCall.getStackCall())));
        }
        return ito_;

    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableNameFirst);
        v_.removeKey(variableNameSecond);
        _ip.removeRefVar(variableNameFirst);
        _ip.removeRefVar(variableNameSecond);
    }

    public void processLastElementLoop(BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<AbstractWrapper> varsInfos_ = ip_.getRefParams();
        ConditionReturn has_ = iteratorHasNext(_advStandards, _ctx, _loopBlock, _rendStack);
        if (has_ == ConditionReturn.CALL_EX) {
            return;
        }
        boolean hasNext_ = has_ == ConditionReturn.YES;
        if (hasNext_) {
            incrementLoop(_loopBlock, vars_,varsInfos_, _advStandards, _ctx, _rendStack);
        } else {
            _loopBlock.getContent().setFinished(true);
        }
    }
    public void incrementLoop(RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars, StringMap<AbstractWrapper> _varsInfos, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        Struct iterator_ = _l.getContent().getStructIterator();
        ImportingPage call_ = _rendStackCall.getLastPage();
        Argument nextPair_ = nextPair(iterator_, _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        Struct value_ = nextPair_.getStruct();
        Argument arg_ = first(value_, _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        if (!ExecInheritsAdv.checkQuick(_rendStackCall.formatVarType(importedClassNameFirst), Argument.getNullableValue(arg_).getStruct().getClassName(_ctx), _ctx, _rendStackCall.getStackCall())) {
            return;
        }
        LoopVariable lv_ = _vars.getVal(variableNameFirst);
        AbstractWrapper lInfo_ = _varsInfos.getVal(variableNameFirst);
        lInfo_.setValue(_rendStackCall.getStackCall(), _ctx,arg_);
        lv_.setIndex(lv_.getIndex() + 1);
        arg_ = second(value_, _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        if (!ExecInheritsAdv.checkQuick(_rendStackCall.formatVarType(importedClassNameSecond), Argument.getNullableValue(arg_).getStruct().getClassName(_ctx), _ctx, _rendStackCall.getStackCall())) {
            return;
        }
        lv_ = _vars.getVal(variableNameSecond);
        lInfo_ = _varsInfos.getVal(variableNameSecond);
        lInfo_.setValue(_rendStackCall.getStackCall(), _ctx,arg_);
        lv_.setIndex(lv_.getIndex() + 1);
        call_.getRendReadWrite().setRead(getFirstChild());
    }
    private static ConditionReturn iteratorHasNext(BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _rendLastStack, RendStackCall _rendStackCall) {
        Struct strIter_ = _rendLastStack.getContent().getStructIterator();
        Argument arg_ = hasNextPair(strIter_, _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
}
