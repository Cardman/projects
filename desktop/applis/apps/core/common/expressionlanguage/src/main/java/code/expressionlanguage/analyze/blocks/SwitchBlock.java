package code.expressionlanguage.analyze.blocks;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.files.OffsetStringInfo;
import code.expressionlanguage.analyze.files.OffsetsBlock;
import code.expressionlanguage.analyze.instr.ElUtil;
import code.expressionlanguage.analyze.opers.Calculation;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.util.*;

public final class SwitchBlock extends BracedBlock implements BreakableBlock,BuildableElMethod {

    private String label;
    private int labelOffset;

    private final String value;
    private int valueOffset;

    private AnaClassArgumentMatching result;

    private boolean enumTest;
    private String instanceTest = "";

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
    public void buildExpressionLanguageReadOnly(AnalyzedPageEl _page) {
        MemberCallingsBlock f_ = _page.getCurrentFct();
        _page.setGlobalOffset(valueOffset);
        _page.setOffset(0);
        CustList<ExecOperationNode> op_ = ElUtil.getAnalyzedOperationsReadOnly(value, Calculation.staticCalculation(f_.getStaticContext()), _page);
        err = _page.getCurrentEmptyPartErr();
        result = _page.getCurrentRoot().getResultClass();
        processAfterEl(_page);
        ExecBracedBlock exec_;
        root = _page.getCurrentRoot();
        if (!instanceTest.isEmpty()) {
            exec_ = new ExecInstanceSwitchBlock(getOffset(), label, valueOffset, op_);
        } else if (enumTest) {
            exec_ = new ExecEnumSwitchBlock(getOffset(), label, valueOffset, op_);
        } else {
            exec_ = new ExecStdSwitchBlock(getOffset(), label, valueOffset, op_);
        }
        exec_.setFile(_page.getBlockToWrite().getFile());
        _page.getBlockToWrite().appendChild(exec_);
        _page.getAnalysisAss().getMappingBracedMembers().put(this,exec_);
        _page.getCoverage().putBlockOperations(exec_,this);
    }

    private void processAfterEl(AnalyzedPageEl _page) {
        AnalyzedPageEl page_ = _page;
        String type_ = result.getSingleNameOrEmpty();
        if (type_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(getFile().getFileName());
            un_.setIndexFile(valueOffset);
            //one char => change to first left par
            un_.buildError(_page.getAnalysisMessages().getUnknownType(),
                    type_);
            _page.addLocError(un_);
            setReachableError(true);
            getErrorsBlock().add(un_.getBuiltError());
        } else {
            String id_ = StringExpUtil.getIdFromAllTypes(type_);
            AnaGeneType classBody_ = page_.getAnaGeneType(id_);
            boolean final_ = true;
            if (classBody_ != null) {
                final_ = ContextUtil.isFinalType(classBody_);
            } else if (type_.startsWith("#")||type_.startsWith("[")) {
                final_ = false;
            }
            if (!AnaTypeUtil.isPrimitiveOrWrapper(id_, _page)) {
                if (!StringList.quickEq(id_, page_.getStandards().getAliasString())) {
                    if (!(classBody_ instanceof EnumBlock)) {
                        if (!final_) {
                            instanceTest = type_;
                        } else {
                            FoundErrorInterpret un_ = new FoundErrorInterpret();
                            un_.setFileName(getFile().getFileName());
                            un_.setIndexFile(valueOffset);
                            //one char => change to first left par
                            un_.buildError(_page.getAnalysisMessages().getUnexpectedType(),
                                    id_);
                            _page.addLocError(un_);
                            setReachableError(true);
                            getErrorsBlock().add(un_.getBuiltError());
                        }
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
            un_.buildError(_page.getAnalysisMessages().getUnexpectedSwitch(),
                    _page.getKeyWords().getKeyWordSwitch(),
                    StringList.join(
                            new StringList(
                                    _page.getKeyWords().getKeyWordCase(),
                                    _page.getKeyWords().getKeyWordDefault()
                            ),
                            "|"));
            _page.addLocError(un_);
            first_.getErrorsBlock().add(un_.getBuiltError());
            first_.setReachableError(true);
            first_ = first_.getNextSibling();
        }
        page_.getCoverage().putBlockOperationsSwitchs(this,def_);
    }

    @Override
    public void abrupt(AnalyzingEl _anEl) {
        Block ch_ = getFirstChild();
        if (ch_ == null) {
            return;
        }
        boolean abrupt_ = true;
        boolean def_ = hasDefaultCase();
        if (!def_) {
            abrupt_ = false;
        } else if (!instanceTest.isEmpty()) {
            CustList<Block> group_ = new CustList<Block>();
            for (Block b: ClassesUtil.getDirectChildren(this)) {
                group_.add(b);
            }
            boolean canCmpNormally_ = false;
            for (Block b: group_) {
                if (_anEl.canCompleteNormally(b)) {
                    canCmpNormally_ = true;
                    break;
                }
            }
            if (canCmpNormally_) {
                abrupt_ = false;
            }
        } else {
            while (ch_.getNextSibling() != null) {
                ch_ = ch_.getNextSibling();
            }
            if (_anEl.canCompleteNormally(ch_)) {
                abrupt_ = false;
            }
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

    public AnaClassArgumentMatching getResult() {
        return result;
    }

    public OperationNode getRoot() {
        return root;
    }

    public String getErr() {
        return err;
    }

    public String getInstanceTest() {
        return instanceTest;
    }
}
