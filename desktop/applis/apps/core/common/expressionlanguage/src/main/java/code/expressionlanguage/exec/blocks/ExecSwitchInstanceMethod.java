package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecAnonFctContent;

public final class ExecSwitchInstanceMethod extends ExecAbstractSwitchMethod {

    public ExecSwitchInstanceMethod(boolean _retRef, String _name, MethodAccessKind _modifier, String _importedParamType, int _offsetTrim, String _retType, ExecAnonFctContent _anonFctContent) {
        super(_retRef, _name, _modifier, _importedParamType, _offsetTrim, _retType, _anonFctContent);
    }

    @Override
    public ExecBlock processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack) {
        ExecBracedBlock found_ = ExecInstanceSwitchBlock.innerProcess(this,getImportedParamType(),_cont,_if,_arg,_stack);
        cover(_cont,_if,_arg,_stack,found_);
        return found_;
    }

}
