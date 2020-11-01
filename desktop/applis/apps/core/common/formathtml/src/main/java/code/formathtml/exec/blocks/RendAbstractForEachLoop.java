package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringMap;

public abstract class RendAbstractForEachLoop extends RendParentBlock implements RendLoop {

    private String label;

    private String importedClassName;

    private String importedClassIndexName;

    private String variableName;

    private int expressionOffset;

    private CustList<RendDynOperationNode> opList;

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
        StringMap<LocalVariable> vInfo_ = _ip.getValueVars();
        vInfo_.removeKey(variableName);
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _cont.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            processBlockAndRemove(_cont, _stds, _ctx);
            return;
        }
        Struct its_ = processLoop(_cont, _stds, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        RendLoopBlockStack l_ = newLoopBlockStack(_cont,_stds,_ctx,label,its_);
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
        Struct struct_ = ExecClassArgumentMatching.defaultValue(importedClassName, _ctx);
        lv_.setIndexClassName(importedClassIndexName);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        varsLoop_.put(variableName, lv_);
        ip_.putValueVar(variableName, LocalVariable.newLocalVariable(struct_,importedClassName));
        processLastElementLoop(_cont, _stds, _ctx, l_);
    }

    private Struct processLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
        ImportingPage ip_ = _conf.getLastPage();
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrList());
        Argument arg_ = RenderExpUtil.calculateReuse(opList,_conf, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        return arg_.getStruct();

    }
    @Override
    public void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock) {
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<LocalVariable> varsInfos_ = ip_.getValueVars();
        ConditionReturn hasNext_ = hasNext(_conf,_advStandards,_ctx,_loopBlock);
        if (hasNext_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (hasNext_ == ConditionReturn.YES) {
            incrementLoop(_conf, _loopBlock, vars_,varsInfos_, _advStandards, _ctx);
        } else {
            _loopBlock.setFinished(true);
        }
    }

    private void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                               StringMap<LoopVariable> _vars,
                               StringMap<LocalVariable> _varsInfos, BeanLgNames _advStandards, ContextEl _ctx) {
        _l.setIndex(_l.getIndex() + 1);
        ImportingPage abs_ = _conf.getLastPage();

//        abs_.setGlobalOffset(variableNameOffset);
        LoopVariable lv_ = _vars.getVal(variableName);
        LocalVariable lInfo_ = _varsInfos.getVal(variableName);
        Argument arg_ = retrieveValue(_conf,_advStandards,_ctx,_l);
        if (_ctx.callsOrException()) {
            return;
        }
        if (!ExecTemplates.checkQuick(importedClassName, arg_, _ctx)) {
            return;
        }
        lInfo_.setStruct(arg_.getStruct());
        lv_.setIndex(lv_.getIndex() + 1);
        abs_.getRendReadWrite().setRead(getFirstChild());
    }
    protected abstract RendLoopBlockStack newLoopBlockStack(Configuration _conf, BeanLgNames _stds, ContextEl _cont, String _label, Struct _its);

    protected abstract Argument retrieveValue(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l);

    protected abstract ConditionReturn hasNext(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _l);

}
