package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
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

public abstract class RendAbstractForEachLoop extends RendParentBlock implements RendLoop {

    private final String label;

    private final String importedClassName;

    private final String importedClassIndexName;

    private final String variableName;

    private final int expressionOffset;

    private final CustList<RendDynOperationNode> opList;

    protected RendAbstractForEachLoop(String _importedClassName, String _variable,
                                      int _expressionOffset, String _classIndex, String _label, int _offsetTrim,CustList<RendDynOperationNode> _res) {
        super(_offsetTrim);
        importedClassName = _importedClassName;
        variableName = _variable;
        expressionOffset = _expressionOffset;
        importedClassIndexName = _classIndex;
        label = _label;
        opList = _res;
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
            processBlockAndRemove(_cont, _stds, _ctx, _rendStack);
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
        l_.setLoop(this);
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
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrList());
        Argument arg_ = RenderExpUtil.calculateReuse(opList, _advStandards, _ctx, _rendStackCall);
        if (_ctx.callsOrException(_rendStackCall.getStackCall())) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();

    }
    @Override
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
            _loopBlock.setFinished(true);
        }
    }

    private void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                               StringMap<LoopVariable> _vars,
                               StringMap<AbstractWrapper> _varsInfos, BeanLgNames _advStandards, ContextEl _ctx, RendStackCall _rendStackCall) {
        _l.setIndex(_l.getIndex() + 1);
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
