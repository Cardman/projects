package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.util.CustList;

public abstract class ExecEnumValueSwitchBlock extends ExecAbstractSwitchBlock {

    ExecEnumValueSwitchBlock(String _label, int _valueOffset, CustList<ExecOperationNode> _opValue, int _offsetTrim) {
        super(_label,_valueOffset,_opValue, _offsetTrim);
    }

    @Override
    protected void processCase(ContextEl _cont,SwitchBlockStack if_, Argument arg_) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        ExecBlock n_ = getFirstChild();
        CustList<ExecBracedBlock> children_;
        children_ = new CustList<ExecBracedBlock>();
        while (n_ instanceof ExecBracedBlock) {
            children_.add((ExecBracedBlock)n_);
            if_.setExecLastVisitedBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        if_.setExecBlock(this);
        ExecBracedBlock found_ = null;
        if (arg_.isNull()) {
            for (ExecBracedBlock b: children_) {
                if (b instanceof ExecDefaultCondition) {
                    found_ = b;
                    continue;
                }
                if (b instanceof ExecNullCaseCondition) {
                    found_ = b;
                    break;
                }
            }
        } else {
            found_ = process(children_,arg_);
        }
        if (found_ == null) {
            _cont.getCoverage().passSwitch(_cont, this, arg_);
            if_.setCurrentVisitedBlock(this);
        } else {
            _cont.getCoverage().passSwitch(_cont, this, found_,arg_);
            rw_.setBlock(found_);
            if_.setCurrentVisitedBlock(found_);
        }
        ip_.addBlock(if_);
    }

    protected abstract ExecBracedBlock process(CustList<ExecBracedBlock> children_, Argument arg_);
}
