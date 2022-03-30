package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendForEachTable;
import code.formathtml.exec.blocks.RendOperationNodeListOff;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class NatRendForEachTable extends RendParentBlock implements NatRendWithEl {

    private final String label;

    private final String variableNameFirst;


    private final String variableNameSecond;
    private final RendOperationNodeListOff exp;

    public NatRendForEachTable(String _variable,
                               String _variableSec,
                               String _label, RendOperationNodeListOff _exp) {
        variableNameFirst = _variable;
        variableNameSecond = _variableSec;
        label = _label;
        exp = _exp;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            RendBlockHelp.processVisitedLoop(this, _rendStack);
//            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        Struct its_ = processLoopTable(_stds, _rendStack);
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Argument arg_ = RendBlockHelp.iteratorMultTable(its_);
        RendLoopBlockStack l_ = RendForEachTable.addedStack(ip_, its_, length_, arg_, label, this);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        Struct defFirst_ = NullStruct.NULL_VALUE;
        varsLoop_.put(variableNameFirst, lv_);
        ip_.putValueVar(variableNameFirst, new VariableWrapperNat(LocalVariable.newLocalVariable(defFirst_,"")));
        lv_ = new LoopVariable();
        lv_.setIndex(-1);
        Struct defSecond_ = NullStruct.NULL_VALUE;
        varsLoop_.put(variableNameSecond, lv_);
        ip_.putValueVar(variableNameSecond, new VariableWrapperNat(LocalVariable.newLocalVariable(defSecond_,"")));
        processLastElementLoop(l_, _rendStack);
    }

    Struct processLoopTable(BeanLgNames _advStandards, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        ip_.setOffset(exp.getOffset());
        Argument arg_ = Argument.getNullableValue(((BeanNatCommonLgNames)_advStandards).getAllArgs(exp.getList(), _rendStackCall).lastValue().getArgument());
        return arg_.getStruct();

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

    public void processLastElementLoop(RendLoopBlockStack _loopBlock, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<AbstractWrapper> varsInfos_ = ip_.getRefParams();
        ConditionReturn has_ = iteratorHasNext(_loopBlock);
        boolean hasNext_ = has_ == ConditionReturn.YES;
        if (hasNext_) {
            incrementLoop(_loopBlock, vars_,varsInfos_, _rendStack);
        } else {
            _loopBlock.getContent().setFinished(true);
        }
    }
    public void incrementLoop(RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars, StringMap<AbstractWrapper> _varsInfos, RendStackCall _rendStackCall) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        Struct iterator_ = _l.getContent().getStructIterator();
        ImportingPage call_ = _rendStackCall.getLastPage();
        Argument nextPair_ = RendBlockHelp.nextCom(iterator_);
        Struct value_ = nextPair_.getStruct();
        Argument arg_ = RendBlockHelp.first(value_);
        LoopVariable lv_ = _vars.getVal(variableNameFirst);
        AbstractWrapper lInfo_ = _varsInfos.getVal(variableNameFirst);
        lInfo_.setValue(_rendStackCall.getStackCall(), null,arg_);
        lv_.setIndex(lv_.getIndex() + 1);
        arg_ = RendBlockHelp.second(value_);
        lv_ = _vars.getVal(variableNameSecond);
        lInfo_ = _varsInfos.getVal(variableNameSecond);
        lInfo_.setValue(_rendStackCall.getStackCall(), null,arg_);
        lv_.setIndex(lv_.getIndex() + 1);
        call_.getRendReadWrite().setRead(getFirstChild());
    }
    private static ConditionReturn iteratorHasNext(RendLoopBlockStack _rendLastStack) {
        Struct strIter_ = _rendLastStack.getContent().getStructIterator();
        Argument arg_ = RendBlockHelp.nasNextCom(strIter_);
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
}
