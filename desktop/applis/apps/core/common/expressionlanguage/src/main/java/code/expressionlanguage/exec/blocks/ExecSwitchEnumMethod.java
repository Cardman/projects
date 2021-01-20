package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;

public final class ExecSwitchEnumMethod extends ExecAbstractSwitchMethod {

    public ExecSwitchEnumMethod(boolean _retRef, String _name, MethodAccessKind _modifier, String _importedParamType, int _offsetTrim) {
        super(_retRef, _name, _modifier, _importedParamType, _offsetTrim);
    }

    @Override
    public ExecBlock processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack, AbstractPageEl _page) {
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
            found_ = ExecEnumSwitchBlock.innerProcess(children_, _arg);
        }
        cover(_cont,_if,_arg,_stack,_page,found_);
        _page.addBlock(_if);
        return found_;
    }
}
