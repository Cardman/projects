package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.util.CustList;

public abstract class ExecEnumValueSwitchBlock extends ExecAbstractSwitchBlock {

    ExecEnumValueSwitchBlock(String _label, int _valueOffset, CustList<ExecOperationNode> _opValue, int _offsetTrim) {
        super(_label,_valueOffset,_opValue, _offsetTrim);
    }

    @Override
    protected void processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ExecBlock n_ = getFirstChild();
        CustList<ExecBracedBlock> children_;
        children_ = new CustList<ExecBracedBlock>();
        while (n_ instanceof ExecBracedBlock) {
            children_.add((ExecBracedBlock)n_);
            _if.setExecLastVisitedBlock((ExecBracedBlock) n_);
            n_ = n_.getNextSibling();
        }
        _if.setExecBlock(this);
        ExecBracedBlock found_ = null;
        if (_arg.isNull()) {
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
            found_ = process(children_, _arg);
        }
        if (found_ == null) {
            _cont.getCoverage().passSwitch(_cont, this, _arg);
            _if.setCurrentVisitedBlock(this);
        } else {
            _cont.getCoverage().passSwitch(_cont, this, found_, _arg);
            ip_.setBlock(found_);
            _if.setCurrentVisitedBlock(found_);
        }
        ip_.addBlock(_if);
    }

    protected abstract ExecBracedBlock process(CustList<ExecBracedBlock> _children, Argument _arg);
}
