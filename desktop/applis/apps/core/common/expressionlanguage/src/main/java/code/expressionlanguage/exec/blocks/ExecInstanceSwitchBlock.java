package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
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
    protected void processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecBracedBlock found_ = innerProcess(this,_cont, _if, _arg, _stack);
        addStack(_cont, _if, _arg, _stack, found_);
    }

    public static ExecBracedBlock innerProcess(ExecBracedBlock _braced, ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecBlock n_ = _braced.getFirstChild();
        CustList<ExecBracedBlock> children_;
        children_ = new CustList<ExecBracedBlock>();
        while (n_ instanceof ExecBracedBlock) {
            children_.add((ExecBracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        _if.setExecBlock(_braced);
        ExecBracedBlock found_ = null;
        if (_arg.isNull()) {
            for (ExecBracedBlock b: children_) {
                if (b instanceof ExecNullInstanceCaseCondition) {
                    _if.setExecLastVisitedBlock(b);
                    found_ = b;
                    break;
                }
            }
        } else {
            for (ExecBracedBlock b: children_) {
                if (b instanceof ExecInstanceCaseCondition) {
                    ExecAbstractInstanceTypeCaseCondition b_ = (ExecAbstractInstanceTypeCaseCondition) b;
                    found_ = fetch(_cont, _if, _arg,found_,b_, _stack);
                }
            }
        }
        if (found_ == null) {
            for (ExecBracedBlock b: children_) {
                if (b instanceof ExecInstanceDefaultCondition) {
                    ExecAbstractInstanceTypeCaseCondition b_ = (ExecAbstractInstanceTypeCaseCondition) b;
                    found_ = fetch(_cont, _if, _arg,found_,b_, _stack);
                }
            }
        }
        return found_;
    }

    private static ExecBracedBlock fetch(ContextEl _cont, SwitchBlockStack _if, Argument _arg,
                                         ExecBracedBlock _found, ExecAbstractInstanceTypeCaseCondition _s, StackCall _stackCall) {
        if (_found != null) {
            return _found;
        }
        String type_ = _s.getImportedClassName();
        AbstractPageEl ip_ = _stackCall.getLastPage();
        type_ = _stackCall.formatVarType(type_);
        if (ExecTemplates.safeObject(type_, _arg, _cont) == ErrorType.NOTHING) {
            String var_ = _s.getVariableName();
            ip_.putValueVar(var_,LocalVariable.newLocalVariable(_arg.getStruct(),type_));
            _if.setExecLastVisitedBlock(_s);
            return _s;
        }
        return null;
    }
}
