package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
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

public final class RendForEachTable extends RendParentBlock implements RendLoop, RendWithEl {

    private final String label;

    private final String importedClassNameFirst;

    private final String importedClassNameSecond;

    private final String importedClassIndexName;

    private final String variableNameFirst;


    private final String variableNameSecond;

    private final int expressionOffset;

    private final CustList<RendDynOperationNode> opList;

    public RendForEachTable(String _className, String _variable,
                     String _classNameSec, String _variableSec,
                     int _expressionOffset, String _classIndex, String _label, int _offsetTrim,CustList<RendDynOperationNode> _opList) {
        super(_offsetTrim);
        importedClassNameFirst = _className;
        variableNameFirst = _variable;
        importedClassNameSecond = _classNameSec;
        variableNameSecond = _variableSec;
        expressionOffset = _expressionOffset;
        importedClassIndexName = _classIndex;
        label = _label;
        opList = _opList;
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
        Struct iterStr_;
        long length_ = IndexConstants.INDEX_NOT_FOUND_ELT;
        Argument arg_ = iteratorMultTable(its_,_cont, _stds, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        iterStr_ = arg_.getStruct();
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLoop(this);
        l_.setLabel(label);
        l_.setIndex(-1);
        l_.setFinished(false);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setStructIterator(iterStr_);
        l_.setMaxIteration(length_);
        l_.setContainer(its_);
        ip_.addBlock(l_);
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndex(-1);
        Struct defFirst_ = ExecClassArgumentMatching.defaultValue(importedClassNameFirst, _ctx);
        lv_.setIndexClassName(importedClassIndexName);
        varsLoop_.put(variableNameFirst, lv_);
        ip_.putValueVar(variableNameFirst, LocalVariable.newLocalVariable(defFirst_,importedClassNameFirst));
        lv_ = new LoopVariable();
        lv_.setIndex(-1);
        Struct defSecond_ = ExecClassArgumentMatching.defaultValue(importedClassNameSecond, _ctx);
        lv_.setIndexClassName(importedClassIndexName);
        varsLoop_.put(variableNameSecond, lv_);
        ip_.putValueVar(variableNameSecond, LocalVariable.newLocalVariable(defSecond_,importedClassNameSecond));
        processLastElementLoop(_cont, _stds, _ctx, l_);
    }

    Struct processLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
        ImportingPage ip_ = _conf.getLastPage();
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrMap());
        Argument arg_ = RenderExpUtil.calculateReuse(opList,_conf, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return NullStruct.NULL_VALUE;
        }
        Struct ito_ = arg_.getStruct();
        if (ito_ == NullStruct.NULL_VALUE) {
            String npe_ = _ctx.getStandards().getContent().getCoreNames().getAliasNullPe();
            _ctx.setCallingState(new CustomFoundExc(new ErrorStruct(_ctx, npe_)));
        }
        return ito_;

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

    @Override
    public void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock) {
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<AbstractWrapper> varsInfos_ = ip_.getRefParams();
        ConditionReturn has_ = iteratorHasNext(_conf, _advStandards, _ctx, _loopBlock);
        if (has_ == ConditionReturn.CALL_EX) {
            return;
        }
        boolean hasNext_ = has_ == ConditionReturn.YES;
        if (hasNext_) {
            incrementLoop(_conf, _loopBlock, vars_,varsInfos_, _advStandards, _ctx);
        } else {
            _loopBlock.setFinished(true);
        }
    }
    public void incrementLoop(Configuration _conf, RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars, StringMap<AbstractWrapper> _varsInfos, BeanLgNames _advStandards, ContextEl _ctx) {
        _l.setIndex(_l.getIndex() + 1);
        Struct iterator_ = _l.getStructIterator();
        ImportingPage call_ = _conf.getLastPage();
        Argument nextPair_ = nextPair(iterator_,_conf, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        Struct value_ = nextPair_.getStruct();
        Argument arg_ = first(value_,_conf, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        if (!ExecTemplates.checkQuick(importedClassNameFirst, arg_, _ctx)) {
            return;
        }
        LoopVariable lv_ = _vars.getVal(variableNameFirst);
        AbstractWrapper lInfo_ = _varsInfos.getVal(variableNameFirst);
        lInfo_.setValue(_ctx,arg_);
        lv_.setIndex(lv_.getIndex() + 1);
        arg_ = second(value_,_conf, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        if (!ExecTemplates.checkQuick(importedClassNameSecond, arg_, _ctx)) {
            return;
        }
        lv_ = _vars.getVal(variableNameSecond);
        lInfo_ = _varsInfos.getVal(variableNameSecond);
        lInfo_.setValue(_ctx,arg_);
        lv_.setIndex(lv_.getIndex() + 1);
        call_.getRendReadWrite().setRead(getFirstChild());
    }
    private ConditionReturn iteratorHasNext(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _rendLastStack) {
        Struct strIter_ = _rendLastStack.getStructIterator();
        Argument arg_ = hasNextPair(strIter_,_conf, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }
}
