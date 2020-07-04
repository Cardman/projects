package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecEnumBlock;
import code.expressionlanguage.exec.blocks.ExecSwitchBlock;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.files.OffsetStringInfo;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.inherits.ClassArgumentMatching;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.*;

public final class SwitchBlock extends BracedBlock implements BreakableBlock,BuildableElMethod {

    private String label;
    private int labelOffset;

    private final String value;
    private int valueOffset;

    private ClassArgumentMatching result;

    private boolean enumTest;

    private OperationNode root;

    private String err = "";

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

    @Override
    public void buildExpressionLanguageReadOnly(ContextEl _cont) {
        MemberCallingsBlock f_ = _cont.getAnalyzing().getCurrentFct();
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        page_.setGlobalOffset(valueOffset);
        page_.setOffset(0);
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(value, _cont, Calculation.staticCalculation(f_.getStaticContext()));
        err = page_.getCurrentEmptyPartErr();
        result = op_.last().getResultClass();
        processAfterEl(_cont);
        ExecSwitchBlock exec_ = new ExecSwitchBlock(getOffset(), label, valueOffset, enumTest, op_);
        root = page_.getCurrentRoot();
        page_.getBlockToWrite().appendChild(exec_);
        page_.getAnalysisAss().getMappingMembers().put(exec_,this);
        page_.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _cont.getCoverage().putBlockOperations(_cont, exec_,this);
    }

    private void processAfterEl(ContextEl _cont) {
        AnalyzedPageEl page_ = _cont.getAnalyzing();
        String type_ = result.getSingleNameOrEmpty();
        if (type_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            //one char => change to first left par
            un_.buildError(_cont.getAnalysisMessages().getUnknownType(),
                    type_);
            _cont.addError(un_);
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
        } else {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            if (!PrimitiveTypeUtil.isPrimitiveOrWrapper(id_, _cont)) {
                if (!StringList.quickEq(id_, _cont.getStandards().getAliasString())) {
                    if (!(_cont.getClassBody(id_) instanceof ExecEnumBlock)) {
                        FoundErrorInterpret un_ = new FoundErrorInterpret();
                        un_.setFileName(getFile().getFileName());
                        un_.setIndexFile(valueOffset);
                        //one char => change to first left par
                        un_.buildError(_cont.getAnalysisMessages().getUnexpectedType(),
                                id_);
                        _cont.addError(un_);
                        setReachableError(true);
                        getErrorsBlock().add(un_.getBuiltError());
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
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(getOffset().getOffsetTrim());
            //key word len
            un_.buildError(_cont.getAnalysisMessages().getUnexpectedSwitch(),
                    _cont.getKeyWords().getKeyWordSwitch(),
                    StringList.join(
                            new StringList(
                                    _cont.getKeyWords().getKeyWordCase(),
                                    _cont.getKeyWords().getKeyWordDefault()
                            ),
                            "|"));
            _cont.addError(un_);
            first_.getErrorsBlock().add(un_.getBuiltError());
            first_.setReachableError(true);
            first_ = first_.getNextSibling();
        }
        _cont.getCoverage().putBlockOperationsSwitchs(_cont,this,def_);
    }

    @Override
    public void abrupt(ContextEl _an, AnalyzingEl _anEl) {
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

    public ClassArgumentMatching getResult() {
        return result;
    }

    public OperationNode getRoot() {
        return root;
    }

    public String getErr() {
        return err;
    }
}
