package code.expressionlanguage.methods;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.AnalyzedPageEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.AbstractPageEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.errors.custom.UnexpectedTagName;
import code.expressionlanguage.errors.custom.UnexpectedTypeError;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stacks.RemovableVars;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.expressionlanguage.structs.EnumerableStruct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public final class SwitchBlock extends BracedStack implements BreakableBlock, WithNotEmptyEl,BuildableElMethod {

    private String label;
    private int labelOffset;

    private final String value;
    private int valueOffset;

    private CustList<ExecOperationNode> opValue;

    private boolean enumTest;

    public SwitchBlock(OffsetStringInfo _value, OffsetStringInfo _label, OffsetsBlock _offset) {
        super(_offset);
        value = _value.getInfo();
        valueOffset = _value.getOffset();
        label = _label.getInfo();
        labelOffset = _label.getOffset();
    }
    
    @Override
    public String getRealLabel() {
        return label;
    }

    public String getLabel() {
        return label;
    }

    public int getLabelOffset() {
        return labelOffset;
    }

    public int getValueOffset() {
        return valueOffset;
    }

    public String getValue() {
        return value;
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opValue);
    }

    public CustList<ExecOperationNode> getOpValue() {
        return opValue;
    }

    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opValue.last();
        opValue = ElUtil.getReducedNodes(r_);
    }
    @Override
    public void setAssignmentBeforeChild(Analyzable _an, AnalyzingEl _anEl) {
        Block firstChild_ = getFirstChild();
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables parAss_ = id_.getVal(this);
        AssignedVariables assBl_ = firstChild_.buildNewAssignedVariable();
        assBl_.getFieldsRootBefore().putAllMap(AssignmentsUtil.assignBefore(parAss_.getLastFieldsOrEmpty()));
        assBl_.getVariablesRootBefore().addAllElts(AssignmentsUtil.assignBefore(parAss_.getLastVariablesOrEmpty()));
        assBl_.getVariablesRootBefore().add(new StringMap<AssignmentBefore>());
        assBl_.getMutableLoopRootBefore().addAllElts(AssignmentsUtil.assignBefore(parAss_.getLastMutableLoopOrEmpty()));
        assBl_.getMutableLoopRootBefore().add(new StringMap<AssignmentBefore>());
        id_.put(firstChild_, assBl_);
    }
    @Override
    public void buildExpressionLanguage(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(f_.isStaticContext()));
        ExecOperationNode op_ = opValue.last();
        ClassArgumentMatching clArg_ = op_.getResultClass();
        if (clArg_.matchVoid(_cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(clArg_);
            _cont.getClasses().addError(un_);
        }
        String type_ = clArg_.getSingleNameOrEmpty();
        if (type_.isEmpty()) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(clArg_);
            _cont.getClasses().addError(un_);
        } else {
            String id_ = Templates.getIdFromAllTypes(type_);
            if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(id_, _cont)) {
                if (!StringList.quickEq(id_, _cont.getStandards().getAliasString())) {
                    if (!(_cont.getClassBody(id_) instanceof EnumBlock)) {
                        UnexpectedTypeError un_ = new UnexpectedTypeError();
                        un_.setFileName(getFile().getFileName());
                        un_.setIndexFile(valueOffset);
                        un_.setType(clArg_);
                        _cont.getClasses().addError(un_);
                    } else {
                        enumTest = true;
                    }
                }
            }
        }
        Block first_ = getFirstChild();
        boolean def_ = false;
        while (first_ != null) {
            Block elt_ = first_;
            if (elt_ instanceof CaseCondition) {
                first_ = first_.getNextSibling();
                continue;
            }
            if (elt_ instanceof DefaultCondition) {
                def_ = true;
                first_ = first_.getNextSibling();
                continue;
            }
            page_.setGlobalOffset(getOffset().getOffsetTrim());
            page_.setOffset(0);
            UnexpectedTagName un_ = new UnexpectedTagName();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            _cont.getClasses().addError(un_);
            first_ = first_.getNextSibling();
        }
        _cont.getCoverage().putBlockOperationsSwitchs(_cont,this,def_);
    }

    @Override
    public void abrupt(Analyzable _an, AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            return;
        }
        boolean abrupt_ = true;
        boolean def_ = hasDefaultCase();
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        if (_anEl.canCompleteNormally(ch_)) {
            abrupt_ = false;
        } else if (!def_) {
            abrupt_ = false;
        }
        IdMap<BreakBlock, BreakableBlock> breakables_;
        breakables_ = _anEl.getBreakables();
        for (EntryCust<BreakBlock, BreakableBlock> e: breakables_.entryList()) {
            if (e.getValue() == this && _anEl.isReachable(e.getKey())) {
                abrupt_ = false;
                break;
            }
        }
        if (abrupt_) {
            _anEl.completeAbruptGroup(this);
        }
    }
    @Override
    public void setAssignmentAfter(Analyzable _an, AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            super.setAssignmentAfter(_an, _anEl);
            return;
        }
        boolean def_ = hasDefaultCase();
        while (ch_.getNextSibling() != null) {
            ch_ = ch_.getNextSibling();
        }
        IdMap<Block, AssignedVariables> id_ = _an.getAssignedVariables().getFinalVariables();
        AssignedVariables assTar_ = id_.getVal(this);
        StringMap<SimpleAssignment> after_ = new StringMap<SimpleAssignment>();
        CustList<StringMap<SimpleAssignment>> afterVars_ = new CustList<StringMap<SimpleAssignment>>();
        CustList<StringMap<SimpleAssignment>> mutableVars_ = new CustList<StringMap<SimpleAssignment>>();
        after_ =buildAssFieldsAfterSwitch(def_, ch_, _an, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterSwitch(def_, ch_, _an, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
        mutableVars_ = buildAssMutableLoopAfterSwitch(def_, ch_, _an, _anEl);
        assTar_.getMutableLoopRoot().clear();
        assTar_.getMutableLoopRoot().addAllElts(mutableVars_);
    }

    private boolean hasDefaultCase() {
        Block ch_ = getFirstChild();
        boolean def_ = false;
        while (ch_.getNextSibling() != null) {
            if (ch_ instanceof DefaultCondition) {
                def_ = true;
            }
            ch_ = ch_.getNextSibling();
        }
        if (ch_ instanceof DefaultCondition) {
            def_ = true;
        }
        return def_;
    }
    @Override
    public void processEl(ContextEl _cont) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        if (ip_.hasBlock()) {
            RemovableVars bl_ = ip_.getLastStack();
            if (bl_.getBlock() == this) {
                ip_.removeLastBlock();
                processBlock(_cont);
                return;
            }
        }
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        Argument arg_ =  el_.calculateMember(_cont);
        if (_cont.callsOrException()) {
            return;
        }
        ip_.clearCurrentEls();
        SwitchBlockStack if_ = new SwitchBlockStack();
        Block n_ = getFirstChild();
        CustList<BracedBlock> children_;
        children_ = new CustList<BracedBlock>();
        while (n_ != null) {
            children_.add((BracedBlock)n_);
            if_.setLastVisitedBlock((BracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        if_.setBlock(this);
        Block def_ = null;
        boolean found_ = false;
        if (arg_.isNull()) {
            for (Block b: children_) {
                if (!(b instanceof CaseCondition)) {
                    def_ = b;
                    continue;
                }
                CaseCondition c_ = (CaseCondition) b;
                Argument argRes_ = c_.getOpValue().last().getArgument();
                if (argRes_ == null) {
                    continue;
                }
                if (argRes_.isNull()) {
                    found_ = true;
                    _cont.getCoverage().passSwitch(_cont,c_,arg_);
                    rw_.setBlock(c_);
                }
            }
        } else if (enumTest) {
            EnumerableStruct en_ = (EnumerableStruct) arg_.getStruct();
            for (Block b: children_) {
                if (!(b instanceof CaseCondition)) {
                    def_ = b;
                    continue;
                }
                CaseCondition c_ = (CaseCondition) b;
                ExecOperationNode op_ = c_.getOpValue().last();
                if (op_.getArgument() != null) {
                    continue;
                }
                if (!StringList.quickEq(c_.getValue().trim(), en_.getName())) {
                    continue;
                }
                found_ = true;
                _cont.getCoverage().passSwitch(_cont,c_,arg_);
                rw_.setBlock(c_);
            }
        } else {
            for (Block b: children_) {
                if (!(b instanceof CaseCondition)) {
                    def_ = b;
                    continue;
                }
                CaseCondition c_ = (CaseCondition) b;
                Argument argRes_ = c_.getOpValue().last().getArgument();
                if (!argRes_.getStruct().sameReference(arg_.getStruct())) {
                    continue;
                }
                found_ = true;
                _cont.getCoverage().passSwitch(_cont,c_,arg_);
                rw_.setBlock(c_);
            }
        }
        if (!found_) {
            if (def_ != null) {
                _cont.getCoverage().passSwitch(_cont,def_,arg_);
                rw_.setBlock(def_);
            } else {
                _cont.getCoverage().passSwitch(_cont,arg_);
                if_.setFinished(true);
            }
        }
        ip_.addBlock(if_);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getEl();
    }
}
