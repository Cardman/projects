package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.IndexConstants;

public final class RendForEachLoop extends RendParentBlock implements RendLoop, RendReducableOperations {

    private String label;

    private String importedClassName;

    private String importedClassIndexName;

    private String variableName;

    private int expressionOffset;

    private CustList<RendDynOperationNode> opList;

    public RendForEachLoop(String _importedClassName, String _variable,
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
        Struct iterStr_ = null;
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        boolean finished_ = false;
        RendDynOperationNode el_ = opList.last();
        if (el_.getResultClass().isArray()) {
            length_ = getLength(its_, _ctx);
            if (length_ == IndexConstants.SIZE_EMPTY) {
                finished_ = true;
            }
            if (_ctx.callsOrException()) {
                return;
            }
        } else {
            if (its_ == NullStruct.NULL_VALUE) {
                String npe_ = _ctx.getStandards().getContent().getCoreNames().getAliasNullPe();
                _ctx.setCallingState(new ErrorStruct(_ctx, npe_));
                return;
            }
            Argument arg_ = iterator(its_,_cont, _stds, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
            iterStr_ = arg_.getStruct();
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLabel(label);
        l_.setLoop(this);
        l_.setIndex(-1);
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        l_.setContainer(its_);
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

    private int getLength(Struct _str, ContextEl _ctx) {
        if (_str instanceof ArrayStruct) {
            return ((ArrayStruct)_str).getLength();
        }
        String npe_ = _ctx.getStandards().getContent().getCoreNames().getAliasNullPe();
        _ctx.setCallingState(new ErrorStruct(_ctx, npe_));
        return -1;
    }
    Struct processLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
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
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        v_.removeKey(variableName);
        StringMap<LocalVariable> vInfo_ = _ip.getValueVars();
        vInfo_.removeKey(variableName);
    }

    @Override
    public void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock) {
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<LocalVariable> varsInfos_ = ip_.getValueVars();
        boolean hasNext_;
        if (_loopBlock.getStructIterator() != null) {
            ConditionReturn has_ = iteratorHasNext(_conf, _advStandards, _ctx, _loopBlock);
            if (has_ == ConditionReturn.CALL_EX) {
                return;
            }
            hasNext_ = has_ == ConditionReturn.YES;
        } else {
            hasNext_ = _loopBlock.hasNext();
        }

        if (hasNext_) {
            incrementLoop(_conf, _loopBlock, vars_,varsInfos_, _advStandards, _ctx);
        } else {
            _loopBlock.setFinished(true);
        }
    }

    private ConditionReturn iteratorHasNext(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _rendLastStack) {
        Struct strIter_ = _rendLastStack.getStructIterator();
        Argument arg_ = hasNext(strIter_,_conf, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    public void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars,
                              StringMap<LocalVariable> _varsInfos, BeanLgNames _advStandards, ContextEl _ctx) {
        _l.setIndex(_l.getIndex() + 1);
        ImportingPage abs_ = _conf.getLastPage();

//        abs_.setGlobalOffset(variableNameOffset);
        LoopVariable lv_ = _vars.getVal(variableName);
        LocalVariable lInfo_ = _varsInfos.getVal(variableName);
        Struct iterator_ = _l.getStructIterator();
        Struct element_ = NullStruct.NULL_VALUE;
        Argument arg_ = Argument.createVoid();
        RendDynOperationNode el_ = opList.last();
        if (!el_.getResultClass().isArray()) {
            arg_ = next(iterator_,_conf, _advStandards, _ctx);
        } else {
            Struct container_ = _l.getContainer();
            LongStruct lg_ = new LongStruct(_l.getIndex());
            element_ = ExecTemplates.getElement(container_, lg_, _ctx);
        }
        if (_ctx.callsOrException()) {
            return;
        }
        if (!el_.getResultClass().isArray()) {
            element_ = arg_.getStruct();
        } else {
            arg_ = new Argument(element_);
        }
        if (!ExecTemplates.checkQuick(importedClassName, arg_, _ctx)) {
            return;
        }
        lInfo_.setStruct(element_);
        lv_.setIndex(lv_.getIndex() + 1);
        abs_.getRendReadWrite().setRead(getFirstChild());
    }
}
