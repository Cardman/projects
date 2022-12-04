package code.bean.nat.exec.blocks;

import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.analyze.NatConfigurationCore;
import code.bean.nat.exec.NatImportingPageAbs;
import code.bean.nat.exec.NatLoopBlockStack;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;

public final class NatRendForEachTable extends NatParentBlock {

    private final String variableNameFirst;


    private final String variableNameSecond;
    private final NatRendOperationNodeListOff exp;

    public NatRendForEachTable(String _variable,
                               String _variableSec,
                               NatRendOperationNodeListOff _exp) {
        variableNameFirst = _variable;
        variableNameSecond = _variableSec;
        exp = _exp;
    }

    @Override
    public void processEl(NatConfigurationCore _cont, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = _rendStack.getLastPage();
        NatLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            RendBlockHelp.processVisitedLoop(this, _rendStack);
//            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
            return;
        }
        Struct its_ = processLoopTable(_rendStack);
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Struct arg_ = RendBlockHelp.iterator(its_);
        NatLoopBlockStack l_ = addedStack(ip_, its_, length_, arg_, this);
        StringMap<Integer> varsLoop_ = ip_.getVars();
        Struct defFirst_ = NullStruct.NULL_VALUE;
        varsLoop_.put(variableNameFirst, -1);
        ip_.putValueVar(variableNameFirst, new VariableWrapperNat(defFirst_));
        Struct defSecond_ = NullStruct.NULL_VALUE;
        varsLoop_.put(variableNameSecond, -1);
        ip_.putValueVar(variableNameSecond, new VariableWrapperNat(defSecond_));
        processLastElementLoop(l_, _rendStack);
    }

    public static NatLoopBlockStack addedStack(NatImportingPageAbs _nip, Struct _its, long _length, Struct _arg, NatParentBlock _block) {
        NatLoopBlockStack nl_ = stElt(_its, _length, _arg, _block);
        _nip.addBlock(nl_);
        return nl_;
    }

    public static NatLoopBlockStack stElt(Struct _its, long _length, Struct _arg, NatParentBlock _block) {
        NatLoopBlockStack l_ = new NatLoopBlockStack();
        l_.getContent().setIndex(-1);
        l_.getContent().setFinished(false);
        l_.setBlock(_block);
        l_.setCurrentVisitedBlock(_block);
        l_.getContent().setStructIterator(_arg);
        l_.getContent().setMaxIteration(_length);
        l_.getContent().setContainer(_its);
        return l_;
    }
    Struct processLoopTable(NatRendStackCall _rendStackCall) {
        return BeanNatCommonLgNames.getAllArgs(exp.getList(), _rendStackCall).lastValue().getArgument();

    }

    public void processLastElementLoop(NatLoopBlockStack _loopBlock, NatRendStackCall _rendStack) {
        NatImportingPageAbs ip_ = _rendStack.getLastPage();
        StringMap<Integer> vars_ = ip_.getVars();
        StringMap<VariableWrapperNat> varsInfos_ = ip_.getRefParams();
        BoolVal has_ = iteratorHasNext(_loopBlock);
        boolean hasNext_ = has_ == BoolVal.TRUE;
        if (hasNext_) {
            incrementLoop(_loopBlock, vars_,varsInfos_, _rendStack);
        } else {
            _loopBlock.getContent().setFinished(true);
        }
    }
    public void incrementLoop(NatLoopBlockStack _l,
                              StringMap<Integer> _vars, StringMap<VariableWrapperNat> _varsInfos, NatRendStackCall _rendStackCall) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        Struct iterator_ = _l.getContent().getStructIterator();
        NatImportingPageAbs call_ = _rendStackCall.getLastPage();
        Struct value_ = RendBlockHelp.nextCom(iterator_);
        Struct arg_ = RendBlockHelp.first(value_);
        Integer lv_ = _vars.getVal(variableNameFirst);
        VariableWrapperNat lInfo_ = _varsInfos.getVal(variableNameFirst);
        lInfo_.setValue(arg_);
        _vars.put(variableNameFirst,lv_+1);
        arg_ = RendBlockHelp.second(value_);
        lv_ = _vars.getVal(variableNameSecond);
        lInfo_ = _varsInfos.getVal(variableNameSecond);
        lInfo_.setValue(arg_);
        _vars.put(variableNameSecond,lv_+1);
        call_.getRendReadWrite().setRead(getFirstChild());
    }

    public void removeAllVars(NatImportingPageAbs _ip) {
        StringMap<Integer> v_ = _ip.getVars();
        v_.removeKey(variableNameFirst);
        v_.removeKey(variableNameSecond);
        _ip.removeRefVar(variableNameFirst);
        _ip.removeRefVar(variableNameSecond);
    }
    private static BoolVal iteratorHasNext(NatLoopBlockStack _rendLastStack) {
        Struct strIter_ = _rendLastStack.getContent().getStructIterator();
        Struct arg_ = RendBlockHelp.nasNextCom(strIter_);
        if (BooleanStruct.isTrue(arg_)) {
            return BoolVal.TRUE;
        }
        return BoolVal.FALSE;
    }
}
