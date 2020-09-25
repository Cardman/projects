package code.expressionlanguage.exec.opers;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;

public abstract class ExecAbstractInvokingConstructor extends ExecInvokingOperation {

    private ExecInvokingConstructorContent invokingConstructorContent;

    private ExecRootBlock rootBlock;
    private ExecNamedFunctionBlock ctor;
    protected ExecAbstractInvokingConstructor(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, ExecRootBlock _rootBlock, ExecNamedFunctionBlock _ctor) {
        super(_opCont, _intermediateDottedOperation);
        invokingConstructorContent = _invokingConstructorContent;
        rootBlock = _rootBlock;
        ctor = _ctor;
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

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public ExecNamedFunctionBlock getCtor() {
        return ctor;
    }
}
