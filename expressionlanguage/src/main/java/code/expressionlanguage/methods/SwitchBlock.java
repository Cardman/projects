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
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.util.StandardCoverageResult;
import code.expressionlanguage.opers.Calculation;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.opers.exec.ExecOperationNode;
import code.expressionlanguage.opers.util.*;
import code.expressionlanguage.stacks.SwitchBlockStack;
import code.expressionlanguage.structs.EnumerableStruct;
import code.util.*;

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

    @Override
    public int getRealLabelOffset() {
        return getLabelOffset();
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
        IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
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
        opValue = ElUtil.getAnalyzedOperations(value, _cont, Calculation.staticCalculation(f_.getStaticContext()));
        processAfterEl(_cont);
    }

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        FunctionBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        opValue = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(f_.getStaticContext()));
        processAfterEl(_cont);
    }

    private void processAfterEl(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        ExecOperationNode op_ = opValue.last();
        ClassArgumentMatching clArg_ = op_.getResultClass();
        if (clArg_.matchVoid(_cont)) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(clArg_);
            _cont.addError(un_);
        }
        String type_ = clArg_.getSingleNameOrEmpty();
        if (type_.isEmpty()) {
            UnexpectedTypeError un_ = new UnexpectedTypeError();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            un_.setType(clArg_);
            _cont.addError(un_);
        } else {
            String id_ = Templates.getIdFromAllTypes(type_);
            if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(id_, _cont)) {
                if (!StringList.quickEq(id_, _cont.getStandards().getAliasString())) {
                    if (!(_cont.getClassBody(id_) instanceof EnumBlock)) {
                        UnexpectedTypeError un_ = new UnexpectedTypeError();
                        un_.setFileName(getFile().getFileName());
                        un_.setIndexFile(valueOffset);
                        un_.setType(clArg_);
                        _cont.addError(un_);
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
            _cont.addError(un_);
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
        IdMap<Block, AssignedVariables> id_ = _an.getContextEl().getAssignedVariables().getFinalVariables();
        AssignedVariables assTar_ = id_.getVal(this);
        StringMap<SimpleAssignment> after_;
        CustList<StringMap<SimpleAssignment>> afterVars_;
        CustList<StringMap<SimpleAssignment>> mutableVars_;
        after_ =buildAssFieldsAfterSwitch(def_, ch_, _an, _anEl);
        assTar_.getFieldsRoot().putAllMap(after_);
        afterVars_ = buildAssVariablesAfterSwitch(def_, ch_, _an, _anEl);
        assTar_.getVariablesRoot().clear();
        assTar_.getVariablesRoot().addAllElts(afterVars_);
        mutableVars_ = buildAssMutableLoopAfterSwitch(def_, ch_, _an, _anEl);
        assTar_.getMutableLoopRoot().clear();
        assTar_.getMutableLoopRoot().addAllElts(mutableVars_);
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int full_ = 0;
        int count_ = 0;
        IdMap<Block, StandardCoverageResult> cases_ = _cont.getCoverage().getCoverSwitchs().getVal(this);
        for (EntryCust<Block, StandardCoverageResult> e: cases_.entryList()) {
            StandardCoverageResult res_ = e.getValue();
            count_ += res_.getCovered();
            full_ += res_.getFull();
        }
        StandardCoverageResult noDef_ = _cont.getCoverage().getCoverNoDefSwitchs().getVal(this);
        if (noDef_ != null) {
            count_ += noDef_.getCovered();
            full_ += noDef_.getFull();
        }
        String tag_;
        if (count_ == full_) {
            tag_ = "<span class=\"f\">";
        } else if (count_ > 0) {
            tag_ = "<span class=\"p\">";
        } else {
            tag_ = "<span class=\"n\">";
        }
        int off_ = getOffset().getOffsetTrim();
        _parts.add(new PartOffset(tag_+"<a title=\""+count_+"/"+full_+"\">",off_));
        tag_ = "</span>";
        _parts.add(new PartOffset("</a>"+tag_,off_+ _cont.getKeyWords().getKeyWordSwitch().length()));
        off_ = getValueOffset();
        int offsetEndBlock_ = off_ + getValue().length();
        ElUtil.buildCoverageReport(_cont,off_,this,getOpValue(),offsetEndBlock_,_parts);
        refLabel(_parts,label,labelOffset);
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
        if (ip_.matchStatement(this)) {
            processBlockAndRemove(_cont);
            return;
        }
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, CustList.FIRST_INDEX, CustList.FIRST_INDEX);
        ip_.setGlobalOffset(valueOffset);
        ip_.setOffset(0);
        Argument arg_ =  ElUtil.tryToCalculate(_cont,el_,0);
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
        BracedBlock found_ = null;
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
                    found_ = c_;
                    break;
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
                if (StringList.quickEq(c_.getValue().trim(), en_.getName())) {
                    found_ = c_;
                    break;
                }
            }
        } else {
            for (Block b: children_) {
                if (!(b instanceof CaseCondition)) {
                    def_ = b;
                    continue;
                }
                CaseCondition c_ = (CaseCondition) b;
                Argument argRes_ = c_.getOpValue().last().getArgument();
                if (argRes_.getStruct().sameReference(arg_.getStruct())) {
                    found_ = c_;
                    break;
                }
            }
        }
        if (found_ == null) {
            if (def_ != null) {
                _cont.getCoverage().passSwitch(_cont,def_,arg_);
                rw_.setBlock(def_);
                if_.setCurrentVisitedBlock((BracedBlock) def_);
            } else {
                _cont.getCoverage().passSwitch(_cont,arg_);
                if_.setCurrentVisitedBlock(this);
            }
        } else {
            _cont.getCoverage().passSwitch(_cont,found_,arg_);
            rw_.setBlock(found_);
            if_.setCurrentVisitedBlock(found_);
        }
        ip_.addBlock(if_);
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context,
            int _indexProcess) {
        return getEl();
    }
}
