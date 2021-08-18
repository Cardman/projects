package code.bean.nat.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.bean.nat.exec.variables.VariableWrapperNat;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.blocks.RendForEachIterable;
import code.formathtml.exec.blocks.RendOperationNodeListOff;
import code.formathtml.exec.blocks.RendParentBlock;
import code.formathtml.exec.blocks.RendWithEl;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public abstract class NatRendAbstractForEachLoop extends RendParentBlock implements RendWithEl {

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
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> vNat_ = _ip.getVars();
        vNat_.removeKey(variableName);
        _ip.removeRefVar(variableName);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            RendBlockHelp.processVisitedLoop(this,_ctx,_rendStack);
            return;
        }
        Struct its_ = processLoop(_cont, _stds, _ctx, _rendStack);
        RendLoopBlockStack l_ = newLoopBlockStack(_ctx,label,its_);
        l_.setLabel(label);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        ip_.addBlock(l_);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        putVar(_rendStack);
        processLastElementLoop(_ctx, l_, _rendStack);
    }
    protected void putVar(RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        Struct struct_ = NullStruct.NULL_VALUE;
        ip_.putValueVar(getVariableName(), new VariableWrapperNat(LocalVariable.newLocalVariable(struct_, "")));
    }
    private Struct processLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        ip_.setOffset(exp.getOffset());
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrList());
        Argument arg_ = RenderExpUtil.calculateReuse(exp.getList(), _advStandards, _ctx, _rendStackCall);
        return arg_.getStruct();

    }

    public void processLastElementLoop(ContextEl _ctx, RendLoopBlockStack _loopBlock, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<AbstractWrapper> varsInfos_ = ip_.getRefParams();
        ConditionReturn hasNext_ = hasNext(_ctx,_loopBlock);
        if (hasNext_ == ConditionReturn.YES) {
            incrementLoop(_loopBlock, vars_,varsInfos_, _ctx, _rendStack);
        } else {
            _loopBlock.getContent().setFinished(true);
        }
    }

    private void incrementLoop(RendLoopBlockStack _l,
                               StringMap<LoopVariable> _vars,
                               StringMap<AbstractWrapper> _varsInfos, ContextEl _ctx, RendStackCall _rendStackCall) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        ImportingPage abs_ = _rendStackCall.getLastPage();

//        abs_.setGlobalOffset(variableNameOffset);
        LoopVariable lv_ = _vars.getVal(variableName);
        Argument arg_ = retrieveValue(_ctx,_l);
        AbstractWrapper lInfo_ = _varsInfos.getVal(variableName);
        lInfo_.setValue(_rendStackCall.getStackCall(), _ctx,arg_);
        lv_.setIndex(lv_.getIndex() + 1);
        abs_.getRendReadWrite().setRead(getFirstChild());
    }
    protected RendLoopBlockStack newLoopBlockStack(ContextEl _cont, String _label, Struct _its) {
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Argument arg_ = RendBlockHelp.iterator(_its, _cont);
        return RendForEachIterable.newRendLoopBlockStack(_label,_its,length_,arg_,this);
    }
    protected Argument retrieveValue(ContextEl _ctx, RendLoopBlockStack _l) {
        return RendBlockHelp.nextCom(_l.getContent().getStructIterator(), _ctx);
    }

    protected ConditionReturn hasNext(ContextEl _ctx, RendLoopBlockStack _l) {
        return iteratorHasNext(_ctx,_l);
    }

    private static ConditionReturn iteratorHasNext(ContextEl _ctx, RendLoopBlockStack _rendLastStack) {
        Struct strIter_ = _rendLastStack.getContent().getStructIterator();
        Argument arg_ = RendBlockHelp.nasNextCom(strIter_, _ctx);
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
    protected String getVariableName() {
        return variableName;
    }

}
