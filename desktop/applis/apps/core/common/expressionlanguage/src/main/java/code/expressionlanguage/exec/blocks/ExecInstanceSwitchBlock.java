package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.util.CustList;

public final class ExecInstanceSwitchBlock extends ExecAbstractSwitchBlock {
    public ExecInstanceSwitchBlock(String _label, int _valueOffset, CustList<ExecOperationNode> _opValue, int _offsetTrim) {
        super(_label, _valueOffset, _opValue, _offsetTrim);
    }

    @Override
    protected void processCase(ContextEl _cont, SwitchBlockStack if_, Argument arg_) {
        AbstractPageEl ip_ = _cont.getLastPage();
        ReadWrite rw_ = ip_.getReadWrite();
        ExecBlock n_ = getFirstChild();
        CustList<ExecBracedBlock> children_;
        children_ = new CustList<ExecBracedBlock>();
        while (n_ instanceof ExecBracedBlock) {
            children_.add((ExecBracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        if_.setExecBlock(this);
        ExecBracedBlock found_ = null;
        if (arg_.isNull()) {
            for (ExecBracedBlock b: children_) {
                if (b instanceof ExecNullInstanceCaseCondition) {
                    if_.setExecLastVisitedBlock(b);
                    found_ = b;
                    break;
                }
            }
        } else {
            for (ExecBracedBlock b: children_) {
                if (b instanceof ExecInstanceCaseCondition) {
                    ExecAbstractInstanceTypeCaseCondition b_ = (ExecAbstractInstanceTypeCaseCondition) b;
                    found_ = fetch(_cont,if_,arg_,found_,b_);
                }
            }
        }
        if (found_ == null) {
            for (ExecBracedBlock b: children_) {
                if (b instanceof ExecInstanceDefaultCondition) {
                    ExecAbstractInstanceTypeCaseCondition b_ = (ExecAbstractInstanceTypeCaseCondition) b;
                    found_ = fetch(_cont,if_,arg_,found_,b_);
                }
            }
        }
        if (found_ == null) {
            _cont.getCoverage().passSwitch(_cont,arg_);
            if_.setCurrentVisitedBlock(this);
        } else {
            _cont.getCoverage().passSwitch(_cont,found_,arg_);
            rw_.setBlock(found_);
            if_.setCurrentVisitedBlock(found_);
        }
        ip_.addBlock(if_);
    }
    private static ExecBracedBlock fetch(ContextEl _cont, SwitchBlockStack if_, Argument arg_,
                                         ExecBracedBlock _found, ExecAbstractInstanceTypeCaseCondition _s) {
        if (_found != null) {
            return _found;
        }
        String type_ = _s.getImportedClassName();
        AbstractPageEl ip_ = _cont.getLastPage();
        type_ = _cont.formatVarType(type_);
        if (ExecTemplates.safeObject(type_, arg_, _cont) == ErrorType.NOTHING) {
            String var_ = _s.getVariableName();
            ip_.putValueVar(var_,LocalVariable.newLocalVariable(arg_.getStruct(),type_));
            if_.setExecLastVisitedBlock(_s);
            return _s;
        }
        return null;
    }
}
