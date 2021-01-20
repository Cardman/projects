package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;

public final class ExecSwitchInstanceMethod extends ExecAbstractSwitchMethod {

    public ExecSwitchInstanceMethod(boolean _retRef, String _name, MethodAccessKind _modifier, String _importedParamType, int _offsetTrim) {
        super(_retRef, _name, _modifier, _importedParamType, _offsetTrim);
    }

    @Override
    public ExecBlock processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack, AbstractPageEl _page) {
        ExecBlock n_ = getFirstChild();
        CustList<ExecBracedBlock> children_;
        children_ = new CustList<ExecBracedBlock>();
        while (n_ instanceof ExecBracedBlock) {
            children_.add((ExecBracedBlock)n_);
            n_ = n_.getNextSibling();
        }
        _if.setExecBlock(this);
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
                    found_ = fetch(_cont, _if, _arg,found_,b_, _page);
                }
            }
        }
        if (found_ == null) {
            for (ExecBracedBlock b: children_) {
                if (b instanceof ExecInstanceDefaultCondition) {
                    ExecAbstractInstanceTypeCaseCondition b_ = (ExecAbstractInstanceTypeCaseCondition) b;
                    found_ = fetch(_cont, _if, _arg,found_,b_, _page);
                }
            }
        }
        _cont.getCoverage().passSwitchMethod(found_, _arg, _stack,_page);
        _page.setBlock(found_);
        _if.setCurrentVisitedBlock(found_);
        _page.addBlock(_if);
        return found_;
    }

    private static ExecBracedBlock fetch(ContextEl _cont, SwitchBlockStack _if, Argument _arg,
                                         ExecBracedBlock _found, ExecAbstractInstanceTypeCaseCondition _s, AbstractPageEl _page) {
        if (_found != null) {
            return _found;
        }
        String type_ = _s.getImportedClassName();
        type_ = _page.formatVarType(type_);
        if (ExecTemplates.safeObject(type_, _arg, _cont) == ErrorType.NOTHING) {
            String var_ = _s.getVariableName();
            _page.putValueVar(var_, LocalVariable.newLocalVariable(_arg.getStruct(),type_));
            _if.setExecLastVisitedBlock(_s);
            return _s;
        }
        return null;
    }
}
