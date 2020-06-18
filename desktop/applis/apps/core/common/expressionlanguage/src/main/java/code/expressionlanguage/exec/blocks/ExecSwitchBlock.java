package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.coverage.StandardCoverageResult;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.files.OffsetsBlock;
import code.expressionlanguage.instr.ElUtil;
import code.expressionlanguage.instr.PartOffset;
import code.expressionlanguage.methods.StackableBlock;
import code.expressionlanguage.methods.WithNotEmptyEl;
import code.expressionlanguage.opers.ExpressionLanguage;
import code.expressionlanguage.stds.ApplyCoreMethodUtil;
import code.util.CustList;
import code.util.StringList;

public final class ExecSwitchBlock extends ExecBracedBlock implements StackableBlock, WithNotEmptyEl {
    private String label;
    private int labelOffset;

    private final String value;
    private int valueOffset;

    private CustList<ExecOperationNode> opValue;

    private boolean enumTest;
    public ExecSwitchBlock(OffsetsBlock _offset, String _label, int _labelOffset, String _value, int _valueOffset, boolean _enumTest, CustList<ExecOperationNode> _opValue) {
        super(_offset);
        label = _label;
        labelOffset = _labelOffset;
        value = _value;
        valueOffset = _valueOffset;
        enumTest = _enumTest;
        opValue = _opValue;
    }

    @Override
    public void processReport(ContextEl _cont, CustList<PartOffset> _parts) {
        int full_ = 0;
        int count_ = 0;
        for (StandardCoverageResult e: _cont.getCoverage().getCoverSwitchs(this).values()) {
            count_ += e.getCovered();
            full_ += e.getFull();
        }
        StandardCoverageResult noDef_ = _cont.getCoverage().getCoverNoDefSwitchs(this);
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
        off_ = valueOffset;
        int offsetEndBlock_ = off_ + value.length();
        ElUtil.buildCoverageReport(_cont,off_,this,opValue,offsetEndBlock_,_parts);
        refLabel(_parts,label,labelOffset);
    }

    public String getRealLabel() {
        return label;
    }

    public int getRealLabelOffset() {
        return labelOffset;
    }

    @Override
    public ExpressionLanguage getEl(ContextEl _context, int _indexProcess) {
        return getEl();
    }

    public ExpressionLanguage getEl() {
        return new ExpressionLanguage(opValue);
    }
    @Override
    public void reduce(ContextEl _context) {
        ExecOperationNode r_ = opValue.last();
        opValue = ElUtil.getReducedNodes(r_);
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
        if_.setLabel(label);
        ExecBlock n_ = getFirstChild();
        CustList<ExecBracedBlock> children_;
        children_ = new CustList<ExecBracedBlock>();
        while (n_ instanceof ExecBracedBlock) {
            children_.add((ExecBracedBlock)n_);
            if_.setExecLastVisitedBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        if_.setExecBlock(this);
        ExecBlock def_ = null;
        ExecBracedBlock found_ = null;
        if (arg_.isNull()) {
            for (ExecBlock b: children_) {
                if (!(b instanceof ExecCaseCondition)) {
                    def_ = b;
                    continue;
                }
                ExecCaseCondition c_ = (ExecCaseCondition) b;
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
            String name_ = ApplyCoreMethodUtil.getNameOfEnum(arg_.getStruct());
            for (ExecBlock b: children_) {
                if (!(b instanceof ExecCaseCondition)) {
                    def_ = b;
                    continue;
                }
                ExecCaseCondition c_ = (ExecCaseCondition) b;
                ExecOperationNode op_ = c_.getOpValue().last();
                if (op_.getArgument() != null) {
                    continue;
                }
                if (StringList.quickEq(c_.getValue().trim(), name_)) {
                    found_ = c_;
                    break;
                }
            }
        } else {
            for (ExecBlock b: children_) {
                if (!(b instanceof ExecCaseCondition)) {
                    def_ = b;
                    continue;
                }
                ExecCaseCondition c_ = (ExecCaseCondition) b;
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
                if_.setCurrentVisitedBlock((ExecBracedBlock) def_);
            } else {
                _cont.getCoverage().passExecSwitch(_cont,arg_);
                if_.setCurrentVisitedBlock(this);
            }
        } else {
            _cont.getCoverage().passSwitch(_cont,found_,arg_);
            rw_.setBlock(found_);
            if_.setCurrentVisitedBlock(found_);
        }
        ip_.addBlock(if_);
    }
}
