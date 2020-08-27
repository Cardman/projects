package code.expressionlanguage.exec.calls.util;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.functionid.MethodId;
import code.util.CustList;

public final class CustomFoundCast implements CallingState {

    private final String className;
    private final ExecRootBlock rootBlock;
    private final ExecNamedFunctionBlock id;

    private final CustList<Argument> arguments;

    public CustomFoundCast(String _className, ExecRootBlock _rootBlock,ExecNamedFunctionBlock _id, CustList<Argument> _arguments) {
        className = _className;
        rootBlock = _rootBlock;
        id = _id;
        arguments = _arguments;
    }

    public String getClassName() {
        return className;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public ExecNamedFunctionBlock getId() {
        return id;
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }
}
