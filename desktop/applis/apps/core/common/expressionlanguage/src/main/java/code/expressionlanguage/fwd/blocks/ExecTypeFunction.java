package code.expressionlanguage.fwd.blocks;

import code.expressionlanguage.exec.blocks.ExecNamedFunctionBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class ExecTypeFunction {
    private final ExecRootBlock type;
    private final ExecNamedFunctionBlock fct;

    public ExecTypeFunction(ExecRootBlock _type, ExecNamedFunctionBlock _fct) {
        type = _type;
        fct = _fct;
    }

    public ExecRootBlock getType() {
        return type;
    }

    public ExecNamedFunctionBlock getFct() {
        return fct;
    }
}
