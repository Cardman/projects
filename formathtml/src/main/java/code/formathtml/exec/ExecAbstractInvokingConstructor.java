package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.AbstractInvokingConstructor;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.CustList;

public abstract class ExecAbstractInvokingConstructor extends ExecInvokingOperation {

    private String methodName;
    private ConstructorId constId;

    private String lastType = EMPTY_STRING;

    private int naturalVararg = -1;
    private int offsetOper;
    public ExecAbstractInvokingConstructor(AbstractInvokingConstructor _abs) {
        super(_abs);
        methodName = _abs.getMethodName();
        constId = _abs.getConstId();
        lastType = _abs.getLastType();
        naturalVararg = _abs.getNaturalVararg();
        offsetOper = _abs.getOffsetOper();
    }

    public int getOffsetOper() {
        return offsetOper;
    }

    abstract Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf);

    public final ConstructorId getConstId() {
        return constId;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getLastType() {
        return lastType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }
}
