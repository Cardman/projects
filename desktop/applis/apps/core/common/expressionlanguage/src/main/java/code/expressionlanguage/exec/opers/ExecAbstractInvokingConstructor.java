package code.expressionlanguage.exec.opers;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class ExecAbstractInvokingConstructor extends ExecInvokingOperation {

    private final ExecFormattedRootBlock formattedType;
    private final ExecInvokingConstructorContent invokingConstructorContent;

    private final ExecTypeFunction pair;

    protected ExecAbstractInvokingConstructor(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, ExecTypeFunction _pair) {
        super(_opCont, _intermediateDottedOperation);
        invokingConstructorContent = _invokingConstructorContent;
        pair = _pair;
        formattedType = _invokingConstructorContent.getFormattedType();
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

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

}
