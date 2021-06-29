package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.blocks.ExecHelperBlocks;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public abstract class RendAbstractForEachLoop extends RendParentBlock implements RendWithEl {

    private final String label;

    private final String importedClassName;

    private final String importedClassIndexName;

    private final String variableName;
    private final RendOperationNodeListOff exp;

    protected RendAbstractForEachLoop(String _importedClassName, String _variable,
                                      int _expressionOffset, String _classIndex, String _label, CustList<RendDynOperationNode> _res) {
        importedClassName = _importedClassName;
        variableName = _variable;
        exp = new RendOperationNodeListOff(_res,_expressionOffset);
        importedClassIndexName = _classIndex;
        label = _label;
    }

    protected static RendLoopBlockStack newLoopBlockStackArr(ContextEl _cont, String _label, Struct _its, RendStackCall _rendStack, RendAbstractForEachLoop _loop) {
        boolean finished_ = false;
        long length_ = ExecHelperBlocks.getLength(_its, _cont, _rendStack.getStackCall());
        if (length_ == IndexConstants.SIZE_EMPTY) {
            finished_ = true;
        }
        if (_cont.callsOrException(_rendStack.getStackCall())) {
            return null;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLabel(_label);
        l_.getContent().setIndex(-1);
        l_.getContent().setFinished(finished_);
        l_.setBlock(_loop);
        l_.setCurrentVisitedBlock(_loop);
        l_.getContent().setMaxIteration(length_);
        l_.getContent().setContainer(_its);
        return l_;
    }

    protected static ConditionReturn hasNext(RendLoopBlockStack _l) {
        return ExecHelperBlocks.hasNext(_l.getContent());
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableName);
        _ip.removeRefVar(variableName);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            processVisitedLoop(_cont,_stds,c_,this,_ctx,_rendStack);
            return;
        }
        Struct its_ = processLoop(_cont, _stds, _ctx, _rendStack);
        if (_ctx.callsOrException(_rendStack.getStackCall())) {
            return;
        }
        RendLoopBlockStack l_ = newLoopBlockStack(_cont,_stds,_ctx,label,its_, _rendStack);
        if (l_ == null) {
            return;
        }
        l_.setLabel(label);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        ip_.addBlock(l_);
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        lv_.setIndexClassName(importedClassIndexName);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        putVar(_ctx,_rendStack,l_);
        processLastElementLoop(_cont, _stds, _ctx, l_, _rendStack);
    }

    protected abstract void putVar(ContextEl _ctx, RendStackCall _rendStack,RendLoopBlockStack _l);
    private Struct processLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        ImportingPage ip_ = _rendStackCall.getLastPage();
        ip_.setOffset(exp.getOffset());
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrList());
        Argument arg_ = RenderExpUtil.calculateReuse(exp.getList(), _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();

    }

    public void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock, RendStackCall _rendStack) {
        ImportingPage ip_ = _rendStack.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<AbstractWrapper> varsInfos_ = ip_.getRefParams();
        ConditionReturn hasNext_ = hasNext(_conf,_advStandards,_ctx,_loopBlock, _rendStack);
        if (hasNext_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (hasNext_ == ConditionReturn.YES) {
            incrementLoop(_conf, _loopBlock, vars_,varsInfos_, _advStandards, _ctx, _rendStack);
        } else {
            _loopBlock.getContent().setFinished(true);
        }
    }

    private void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                               StringMap<LoopVariable> _vars,
                               StringMap<AbstractWrapper> _varsInfos, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        _l.getContent().setIndex(_l.getContent().getIndex() + 1);
        ImportingPage abs_ = _rendStackCall.getLastPage();

//        abs_.setGlobalOffset(variableNameOffset);
        LoopVariable lv_ = _vars.getVal(variableName);
        Argument arg_ = retrieveValue(_conf,_advStandards,_ctx,_l, _rendStackCall);
        AbstractWrapper lInfo_ = _varsInfos.getVal(variableName);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return;
        }
        if (!ExecTemplates.checkQuick(importedClassName, Argument.getNullableValue(arg_).getStruct().getClassName(_ctx), _ctx, _rendStackCall.getStackCall())) {
            return;
        }
        lInfo_.setValue(_rendStackCall.getStackCall(), _ctx,arg_);
        lv_.setIndex(lv_.getIndex() + 1);
        abs_.getRendReadWrite().setRead(getFirstChild());
    }
    protected abstract RendLoopBlockStack newLoopBlockStack(Configuration _conf, BeanLgNames _stds, ContextEl _cont, String _label, Struct _its, RendStackCall _rendStack);

    protected abstract Argument retrieveValue(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l, RendStackCall _rendStack);

    protected abstract ConditionReturn hasNext(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l, RendStackCall _rendStack);

    protected String getVariableName() {
        return variableName;
    }

    protected String getImportedClassName() {
        return importedClassName;
    }
}
