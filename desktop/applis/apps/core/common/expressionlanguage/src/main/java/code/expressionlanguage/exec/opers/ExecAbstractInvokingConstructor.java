package code.expressionlanguage.exec.opers;

import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class ExecAbstractInvokingConstructor extends ExecInvokingOperation {

    private ExecInvokingConstructorContent invokingConstructorContent;

    private ExecTypeFunction pair;
    protected ExecAbstractInvokingConstructor(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, ExecTypeFunction _pair) {
        super(_opCont, _intermediateDottedOperation);
        invokingConstructorContent = _invokingConstructorContent;
        pair = _pair;
    }

    public int getOffsetOper() {
        return invokingConstructorContent.getOffsetOper();
    }

    public String getLastType() {
        return invokingConstructorContent.getLastType();
    }

    public int getNaturalVararg() {
        return invokingConstructorContent.getNaturalVararg();
    }

    public String getClassFromName() {
        return invokingConstructorContent.getClassFromName();
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

}
