package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.BooleanList;
import code.util.StringList;

public abstract class ExecAbstractSwitchMethod extends ExecMemberCallingsBlock implements ExecReturnableWithSignature {

    private final String name;

    private final String importedParamType;

    private final boolean retRef;

    private final MethodAccessKind kind;
    private ExecRootBlock parentType;
    private ExecOperatorBlock operator;

    public ExecAbstractSwitchMethod(boolean _retRef, String _name, MethodAccessKind _modifier, String _importedParamType, int _offsetTrim) {
        super(_offsetTrim);
        name = _name;
        retRef = _retRef;
        importedParamType = _importedParamType;
        kind = _modifier;
    }

    @Override
    public String getSignature(ContextEl _ana) {
        return getId().getSignature(_ana);
    }

    public MethodId getId() {
        String name_ = getName();
        StringList pTypes_ = new StringList(importedParamType);
        return new MethodId(isRetRef(), kind, name_, pTypes_,new BooleanList(false), false);
    }

    public MethodAccessKind getKind() {
        return kind;
    }

    public MethodModifier getModifier() {
        if (kind == MethodAccessKind.STATIC) {
            return MethodModifier.STATIC;
        }
        if (kind == MethodAccessKind.STATIC_CALL) {
            return MethodModifier.STATIC_CALL;
        }
        return MethodModifier.FINAL;
    }
    public String getName() {
        return name;
    }

    public boolean isRetRef() {
        return retRef;
    }

    public ExecRootBlock getParentType() {
        return parentType;
    }

    public void setParentType(ExecRootBlock _parentType) {
        this.parentType = _parentType;
    }

    public ExecOperatorBlock getOperator() {
        return operator;
    }

    public void setOperator(ExecOperatorBlock _operator) {
        this.operator = _operator;
    }

    public abstract ExecBlock processCase(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack, AbstractPageEl _page);
    public static void cover(ContextEl _cont, SwitchBlockStack _if, Argument _arg, StackCall _stack, AbstractPageEl _ip, ExecBracedBlock _found) {
        _cont.getCoverage().passSwitchMethod(_found, _arg, _stack,_ip);
        _ip.setBlock(_found);
        _if.setCurrentVisitedBlock(_found);
    }

}
