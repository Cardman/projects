package code.formathtml.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.ErrorStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.exec.variables.LoopVariable;
import code.expressionlanguage.structs.Struct;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RenderExpUtil;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.stacks.RendLoopBlockStack;
import code.formathtml.stacks.RendReadWrite;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.StringMap;

public final class RendForIterativeLoop extends RendParentBlock implements RendLoop, RendReducableOperations {

    private String label;

    private String importedClassName;
    private String importedClassIndexName;

    private String variableName;

    private int initOffset;

    private int expressionOffset;

    private int stepOffset;

    private boolean eq;
    private CustList<RendDynOperationNode> opInit;

    private CustList<RendDynOperationNode> opExp;

    private CustList<RendDynOperationNode> opStep;

    public RendForIterativeLoop(String _className, String _variable,
                         int _from,
                         int _to, boolean _eq, int _step, String _classIndex, String _label, int _offsetTrim,
                         CustList<RendDynOperationNode> _opInit, CustList<RendDynOperationNode> _opExp, CustList<RendDynOperationNode> _opStep) {
        super(_offsetTrim);
        importedClassName = _className;
        variableName = _variable;
        initOffset = _from;
        expressionOffset = _to;
        stepOffset = _step;
        eq = _eq;
        importedClassIndexName = _classIndex;
        label = _label;
        opInit = _opInit;
        opExp = _opExp;
        opStep = _opStep;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String getRealLabel() {
        return getLabel();
    }

    public String getVariableName() {
        return variableName;
    }

    public boolean isEq() {
        return eq;
    }

    @Override
    public void processEl(Configuration _cont, BeanLgNames _stds, ContextEl _ctx) {
        ImportingPage ip_ = _cont.getLastPage();
        RendLoopBlockStack c_ = ip_.getLastLoopIfPossible(this);
        if (c_ != null) {
            processBlockAndRemove(_cont, _stds, _ctx);
            return;
        }
        processLoop(_cont, _stds, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        c_ = (RendLoopBlockStack) ip_.getRendLastStack();
        if (c_.isFinished()) {
            processBlockAndRemove(_cont, _stds, _ctx);
            return;
        }
        ip_.getRendReadWrite().setRead(getFirstChild());
    }

    private void processLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
        LgNames stds_ = _ctx.getStandards();
        String null_ = stds_.getContent().getCoreNames().getAliasNullPe();
        ImportingPage ip_ = _conf.getLastPage();
        StringMap<LoopVariable> varsLoop_ = ip_.getVars();
        String var_ = getVariableName();
        long nbMaxIterations_ = 0;
        long stepValue_ = 0;
        long fromValue_ = 0;

        boolean eq_ = isEq();
        ip_.setOffset(initOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrFrom());
        Argument argFrom_ = RenderExpUtil.calculateReuse(opInit,_conf, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        if (argFrom_.isNull()) {
            _ctx.setException(new ErrorStruct(_ctx,null_));
            return;
        }
        ip_.setOffset(expressionOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrTo());
        Argument argTo_ = RenderExpUtil.calculateReuse(opExp,_conf, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        if (argTo_.isNull()) {
            _ctx.setException(new ErrorStruct(_ctx,null_));
            return;
        }
        ip_.setOffset(stepOffset);
        ip_.setProcessingAttribute(_conf.getRendKeyWords().getAttrStep());
        Argument argStep_ = RenderExpUtil.calculateReuse(opStep,_conf, _advStandards, _ctx);
        if (_ctx.callsOrException()) {
            return;
        }
        if (argStep_.isNull()) {
            _ctx.setException(new ErrorStruct(_ctx,null_));
            return;
        }
        fromValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argFrom_.getStruct())).longStruct();
        long toValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argTo_.getStruct())).longStruct();
        stepValue_ = NumParsers.convertToInt(PrimitiveTypes.LONG_WRAP, NumParsers.convertToNumber(argStep_.getStruct())).longStruct();
        if (stepValue_ > 0) {
            if (fromValue_ > toValue_) {
                stepValue_ = -stepValue_;
            }
        } else if (stepValue_ < 0) {
            if (fromValue_ < toValue_) {
                stepValue_ = -stepValue_;
            }
        }
        if (stepValue_ > 0) {
            long copyFrom_ = fromValue_;
            while (true) {
                if (copyFrom_ >= toValue_ && !eq_) {
                    break;
                }
                if (copyFrom_ > toValue_) {
                    break;
                }
                nbMaxIterations_++;
                copyFrom_ += stepValue_;
            }
        } else if (stepValue_ < 0) {
            long copyFrom_ = fromValue_;
            while (true) {
                if (copyFrom_ <= toValue_ && !eq_) {
                    break;
                }
                if (copyFrom_ < toValue_) {
                    break;
                }
                nbMaxIterations_++;
                copyFrom_ += stepValue_;
            }
        }
        long length_;
        boolean finished_ = false;
        length_ = nbMaxIterations_;
        if (length_ == CustList.SIZE_EMPTY) {
            finished_ = true;
        }
        RendLoopBlockStack l_ = new RendLoopBlockStack();
        l_.setFinished(finished_);
        l_.setBlock(this);
        l_.setCurrentVisitedBlock(this);
        l_.setMaxIteration(length_);
        l_.setStep(stepValue_);
        ip_.addBlock(l_);
        if (finished_) {
            return;
        }
        LoopVariable lv_ = new LoopVariable();
        lv_.setIndexClassName(importedClassIndexName);
        Struct struct_ = NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _ctx.getStandards().getPrimTypes()), new LongStruct(fromValue_));
        varsLoop_.put(var_, lv_);
        ip_.putValueVar(var_, LocalVariable.newLocalVariable(struct_,importedClassName));
    }

    @Override
    public void exitStack(Configuration _context, BeanLgNames _advStandards, ContextEl _ctx) {
        processLastElementLoop(_context, _advStandards,_ctx);
    }

    @Override
    public void removeAllVars(ImportingPage _ip) {
        super.removeAllVars(_ip);
        StringMap<LoopVariable> v_ = _ip.getVars();
        String var_ = getVariableName();
        v_.removeKey(var_);
        StringMap<LocalVariable> vInfo_ = _ip.getValueVars();
        vInfo_.removeKey(var_);
    }

    @Override
    public void processLastElementLoop(Configuration _conf, BeanLgNames _advStandards, ContextEl _ctx) {
        ImportingPage ip_ = _conf.getLastPage();
        RendReadWrite rw_ = ip_.getRendReadWrite();
        StringMap<LoopVariable> vars_ = ip_.getVars();
        StringMap<LocalVariable> varsInfos_ = ip_.getValueVars();
        RendLoopBlockStack l_ = (RendLoopBlockStack) ip_.getRendLastStack();
        RendBlock forLoopLoc_ = l_.getBlock();
        if (l_.hasNext()) {
            incrementLoop(l_, vars_,varsInfos_, _ctx);
            rw_.setRead(forLoopLoc_.getFirstChild());
            return;
        }
        l_.setFinished(true);
    }

    public void incrementLoop(RendLoopBlockStack _l,
                              StringMap<LoopVariable> _vars, StringMap<LocalVariable> _varsInfos, ContextEl _ctx) {
        _l.setIndex(_l.getIndex() + 1);
        String var_ = getVariableName();
        LoopVariable lv_ = _vars.getVal(var_);
        LocalVariable lInfo_ = _varsInfos.getVal(var_);
        long o_ = NumParsers.convertToNumber(lInfo_.getStruct()).longStruct()+_l.getStep();
        lInfo_.setStruct(NumParsers.convertToInt(ClassArgumentMatching.getPrimitiveCast(importedClassName, _ctx.getStandards().getPrimTypes()), new LongStruct(o_)));
        lv_.setIndex(lv_.getIndex() + 1);
    }
}
