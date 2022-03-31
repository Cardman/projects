package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.exec.ImportingPage;
import code.formathtml.exec.blocks.RendForEachIterable;
import code.formathtml.exec.blocks.RendOperationNodeListOff;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public abstract class NatRendAbstractForEachLoop extends RendParentBlock implements NatRendWithEl {

    private final String label;

    private final String variableName;
    private final RendOperationNodeListOff exp;

    protected NatRendAbstractForEachLoop(String _variable,
                                         int _expressionOffset, String _label, CustList<RendDynOperationNode> _res) {
        variableName = _variable;
        exp = new RendOperationNodeListOff(_res,_expressionOffset);
        label = _label;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            RendBlockHelp.processVisitedLoop(this, _rendStack);
            return;
        }
        Struct its_ = processLoop(_stds, _rendStack);
        RendLoopBlockStack l_ = newLoopBlockStack(label,its_);
        l_.setLabel(label);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        ip_.addBlock(l_);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        putVar(_rendStack);
        processLastElementLoop(l_, _rendStack);
    }
    protected void putVar(NatRendStackCall _rendStack) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        Struct struct_ = NullStruct.NULL_VALUE;
        ip_.putValueVar(getVariableName(), new VariableWrapperNat(LocalVariable.newLocalVariable(struct_, "")));
    }
    private Struct processLoop(BeanLgNames _advStandards, NatRendStackCall _rendStackCall) {
        Argument arg_ = Argument.getNullableValue(((BeanNatCommonLgNames)_advStandards).getAllArgs(exp.getList(), _rendStackCall).lastValue().getArgument());
        return arg_.getStruct();

    }

    public void processLastElementLoop(RendLoopBlockStack _loopBlock, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<VariableWrapperNat> varsInfos_ = ip_.getRefParams();
        ConditionReturn hasNext_ = hasNext(_loopBlock);
        if (hasNext_ == ConditionReturn.YES) {
            incrementLoop(_loopBlock, vars_,varsInfos_, _rendStack);
        } else {
            _loopBlock.getContent().setFinished(true);
        }
    }

    private void incrementLoop(RendLoopBlockStack _l,
                               StringMap<LoopVariable> _vars,
                               StringMap<VariableWrapperNat> _varsInfos, NatRendStackCall _rendStackCall) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        NatImportingPage abs_ = _rendStackCall.getLastPage();

//        abs_.setGlobalOffset(variableNameOffset);
        LoopVariable lv_ = _vars.getVal(variableName);
        Argument arg_ = retrieveValue(_l);
        VariableWrapperNat lInfo_ = _varsInfos.getVal(variableName);
        lInfo_.setValue(arg_);
        lv_.setIndex(lv_.getIndex() + 1);
        abs_.getRendReadWrite().setRead(getFirstChild());
    }
    protected RendLoopBlockStack newLoopBlockStack(String _label, Struct _its) {
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Argument arg_ = RendBlockHelp.iterator(_its);
        return RendForEachIterable.newRendLoopBlockStack(_label,_its,length_,arg_,this);
    }
    protected Argument retrieveValue(RendLoopBlockStack _l) {
        return RendBlockHelp.nextCom(_l.getContent().getStructIterator());
    }

    protected ConditionReturn hasNext(RendLoopBlockStack _l) {
        return iteratorHasNext(_l);
    }

    private static ConditionReturn iteratorHasNext(RendLoopBlockStack _rendLastStack) {
        Struct strIter_ = _rendLastStack.getContent().getStructIterator();
        Argument arg_ = RendBlockHelp.nasNextCom(strIter_);
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
    protected String getVariableName() {
        return variableName;
    }

    public void removeAllVars(NatImportingPage _ip) {
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableName);
        _ip.removeRefVar(variableName);
    }
}
