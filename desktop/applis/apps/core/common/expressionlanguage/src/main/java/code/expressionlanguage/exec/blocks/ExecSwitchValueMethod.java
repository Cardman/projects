package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.util.CustList;

public final class ExecSwitchValueMethod extends ExecAbstractSwitchMethod {

    public ExecSwitchValueMethod(boolean _retRef, String _name, MethodAccessKind _modifier, String _importedParamType, int _offsetTrim) {
        super(_retRef, _name, _modifier, _importedParamType, _offsetTrim);
    }

    @Override
    public ExecBlock processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecBracedBlock found_ = ExecStdSwitchBlock.innerProcess(this, _if, _arg);
        cover(_cont,_if,_arg,_stack, found_);
        return found_;
    }
}
