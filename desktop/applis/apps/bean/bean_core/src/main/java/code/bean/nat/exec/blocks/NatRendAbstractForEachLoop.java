package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.*;
import code.bean.nat.exec.opers.NatExecOperationNode;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public abstract class NatRendAbstractForEachLoop extends NatParentBlock {

    private final String variableName;
    private final NatRendOperationNodeListOff exp;

    protected NatRendAbstractForEachLoop(String _variable,
                                         CustList<NatExecOperationNode> _res) {
        variableName = _variable;
        exp = new NatRendOperationNodeListOff(_res);
    }

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = _rendStack.getLastPage();
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
        StringMap<Integer> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, -1);
        putVar(_rendStack);
        processLastElementLoop(l_, _rendStack);
    }
    protected void putVar(NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = _rendStack.getLastPage();
        Struct struct_ = NullStruct.NULL_VALUE;
        ip_.putValueVar(getVariableName(), new VariableWrapperNat(struct_));
    }
    private Struct processLoop(NatRendStackCall _rendStackCall) {
        return BeanNatCommonLgNames.getAllArgs(exp.getList(), _rendStackCall).lastValue().getArgument();

    }

    public void processLastElementLoop(NatLoopBlockStack _loopBlock, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = _rendStack.getLastPage();
        StringMap<Integer> vars_ = ip_.getVars();
        StringMap<VariableWrapperNat> varsInfos_ = ip_.getRefParams();
        ConditionReturn hasNext_ = hasNext(_loopBlock);
        if (hasNext_ == ConditionReturn.YES) {
            incrementLoop(_loopBlock, vars_,varsInfos_, _rendStack);
        } else {
            _loopBlock.getContent().setFinished(true);
        }
    }

    private void incrementLoop(NatLoopBlockStack _l,
                               StringMap<Integer> _vars,
                               StringMap<VariableWrapperNat> _varsInfos, NatRendStackCall _rendStackCall) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        NatImportingPageAbs abs_ = _rendStackCall.getLastPage();

//        abs_.setGlobalOffset(variableNameOffset);
        int lv_ = _vars.getVal(variableName);
        Struct arg_ = retrieveValue(_l);
        VariableWrapperNat lInfo_ = _varsInfos.getVal(variableName);
        lInfo_.setValue(arg_);
        _vars.put(variableName,lv_+1);
        abs_.getRendReadWrite().setRead(getFirstChild());
    }
    protected NatLoopBlockStack newLoopBlockStack(Struct _its) {
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Struct arg_ = RendBlockHelp.iterator(_its);
        return newRendLoopBlockStack(_its,length_,arg_,this);
    }

    public static NatLoopBlockStack newRendLoopBlockStack(Struct _its, long _length, Struct _arg, NatParentBlock _block) {
        NatLoopBlockStack l_ = new NatLoopBlockStack();
        l_.getContent().setIndex(-1);
        l_.setBlock(_block);
        l_.setCurrentVisitedBlock(_block);
        l_.getContent().setStructIterator(_arg);
        l_.getContent().setMaxIteration(_length);
        l_.getContent().setContainer(_its);
        return l_;
    }
    protected Struct retrieveValue(NatLoopBlockStack _l) {
        return RendBlockHelp.nextCom(_l.getContent().getStructIterator());
    }

    protected ConditionReturn hasNext(NatLoopBlockStack _l) {
        return iteratorHasNext(_l);
    }

    private static ConditionReturn iteratorHasNext(NatLoopBlockStack _rendLastStack) {
        Struct strIter_ = _rendLastStack.getContent().getStructIterator();
        Struct arg_ = RendBlockHelp.nasNextCom(strIter_);
        if (BooleanStruct.isTrue(arg_)) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
    protected String getVariableName() {
        return variableName;
    }

    public void removeAllVars(NatImportingPageAbs _ip) {
        StringMap<Integer> v_ = _ip.getVars();
        v_.removeKey(variableName);
        _ip.removeRefVar(variableName);
    }
}
