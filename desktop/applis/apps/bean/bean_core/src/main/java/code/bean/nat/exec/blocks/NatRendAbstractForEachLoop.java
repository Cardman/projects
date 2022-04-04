package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.exec.NatAbstractStask;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatLoopBlockStack;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public abstract class NatRendAbstractForEachLoop extends NatParentBlock implements NatRendWithEl {

    private final String variableName;
    private final NatRendOperationNodeListOff exp;

    protected NatRendAbstractForEachLoop(String _variable,
                                         CustList<NatExecOperationNode> _res) {
        variableName = _variable;
        exp = new NatRendOperationNodeListOff(_res);
    }

    @Override
    public void processEl(Configuration _cont, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = _rendStack.getLastPage();
        NatAbstractStask c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            RendBlockHelp.processVisitedLoop(this, _rendStack);
            return;
        }
        Struct its_ = processLoop(_rendStack);
        NatLoopBlockStack l_ = newLoopBlockStack(its_);
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
        ip_.putValueVar(getVariableName(), new VariableWrapperNat(struct_));
    }
    private Struct processLoop(NatRendStackCall _rendStackCall) {
        Argument arg_ = Argument.getNullableValue(BeanNatCommonLgNames.getAllArgs(exp.getList(), _rendStackCall).lastValue().getArgument());
        return arg_.getStruct();

    }

    public void processLastElementLoop(NatLoopBlockStack _loopBlock, NatRendStackCall _rendStack) {
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

    private void incrementLoop(NatLoopBlockStack _l,
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
    protected NatLoopBlockStack newLoopBlockStack(Struct _its) {
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Argument arg_ = RendBlockHelp.iterator(_its);
        return newRendLoopBlockStack(_its,length_,arg_,this);
    }

    public static NatLoopBlockStack newRendLoopBlockStack(Struct _its, long _length, Argument _arg, NatParentBlock _block) {
        Struct iterStr_ = _arg.getStruct();
        NatLoopBlockStack l_ = new NatLoopBlockStack();
        l_.getContent().setIndex(-1);
        l_.setBlock(_block);
        l_.setCurrentVisitedBlock(_block);
        l_.getContent().setStructIterator(iterStr_);
        l_.getContent().setMaxIteration(_length);
        l_.getContent().setContainer(_its);
        return l_;
    }
    protected Argument retrieveValue(NatLoopBlockStack _l) {
        return RendBlockHelp.nextCom(_l.getContent().getStructIterator());
    }

    protected ConditionReturn hasNext(NatLoopBlockStack _l) {
        return iteratorHasNext(_l);
    }

    private static ConditionReturn iteratorHasNext(NatLoopBlockStack _rendLastStack) {
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
