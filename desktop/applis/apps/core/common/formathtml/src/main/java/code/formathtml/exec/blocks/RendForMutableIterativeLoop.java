package code.formathtml.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ConditionReturn;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.Struct;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringList;

public final class RendForMutableIterativeLoop extends RendParentBlock implements RendLoop,RendWithEl {


    private String label;

    private String importedClassName = EMPTY_STRING;

    private String importedClassIndexName;

    private StringList variableNames = new StringList();

    private int initOffset;

    private int expressionOffset;

    private int stepOffset;

    private CustList<RendDynOperationNode> opInit;

    private CustList<RendDynOperationNode> opExp;

    private CustList<RendDynOperationNode> opStep;

    public RendForMutableIterativeLoop(String _className,
                                int _from,
                                int _to, int _step,StringList _varNames, String _classIndex, String _label, int _offsetTrim,
                                CustList<RendDynOperationNode> _opInit, CustList<RendDynOperationNode> _opExp, CustList<RendDynOperationNode> _opStep) {
        super(_offsetTrim);
        importedClassName = _className;
        initOffset = _from;
        expressionOffset = _to;
        stepOffset = _step;
        variableNames = _varNames;
        importedClassIndexName = _classIndex;
        label = _label;
        opInit = _opInit;
        opExp = _opExp;
        opStep = _opStep;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _cont.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            processBlockAndRemove(_cont, _stds, _ctx);
            return;
        }
        ip_.setOffset(initOffset);
        ip_.setProcessingAttribute(_cont.getRendKeyWords().getAttrInit());
        Struct struct_ = ExecClassArgumentMatching.defaultValue(importedClassName, _ctx);
        for (String v: variableNames) {
            LoopVariable lv_ = new LoopVariable();
            lv_.setIndexClassName(importedClassIndexName);
            ip_.getVars().put(v, lv_);
            ip_.putValueVar(v, LocalVariable.newLocalVariable(struct_,importedClassName));
        }
        if (!opInit.isEmpty()) {
            RenderExpUtil.calculateReuse(opInit,_cont, _stds, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
        }
        ConditionReturn res_ = evaluateCondition(_cont, _stds, _ctx);
        if (res_ == ConditionReturn.CALL_EX) {
            return;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setLoop(this);
        l_.setLabel(label);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setFinished(res_ == ConditionReturn.NO);
        ip_.addBlock(l_);
        c_ = (RendLoopBlockStack) ip_.getRendLastStack();
        if (c_.isFinished()) {
            processBlockAndRemove(_cont, _stds, _ctx);
            return;
        }
        rw_.setRead(getFirstChild());
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        for (String v: variableNames) {
            _ip.getVars().removeKey(v);
        }
        for (String v: variableNames) {
            _ip.getValueVars().removeKey(v);
        }
    }

    private ConditionReturn evaluateCondition(Configuration _context, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage last_ = _context.getLastPage();
        if (opExp.isEmpty()) {
            return ConditionReturn.YES;
        }
        last_.setOffset(expressionOffset);
        last_.setProcessingAttribute(_context.getRendKeyWords().getAttrCondition());
        Argument arg_ = RenderExpUtil.calculateReuse(opExp,_context, _stds, _ctx);
        if (_ctx.callsOrException()) {
            return ConditionReturn.CALL_EX;
        }
        if (BooleanStruct.isTrue(arg_.getStruct())) {
            return ConditionReturn.YES;
        }
        return ConditionReturn.NO;
    }

    @Override
    public void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx, RendLoopBlockStack _loopBlock) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        RendBlock forLoopLoc_ = l_.getBlock();
        ip_.setOffset(stepOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrStep());
        if (!opStep.isEmpty()) {
            RenderExpUtil.calculateReuse(opStep,_conf, _advStandards, _ctx);
            if (_ctx.callsOrException()) {
                return;
            }
        }
        for (String v: variableNames) {
            LoopVariable lv_ = ip_.getVars().getVal(v);
            lv_.setIndex(lv_.getIndex()+1);
        }
        ConditionReturn keep_ = evaluateCondition(_conf, _advStandards, _ctx);
        if (keep_ == ConditionReturn.CALL_EX) {
            return;
        }
        if (keep_ == ConditionReturn.NO) {
            l_.setFinished(true);
        } else {
            rw_.setRead(forLoopLoc_.getFirstChild());
        }
    }
}
