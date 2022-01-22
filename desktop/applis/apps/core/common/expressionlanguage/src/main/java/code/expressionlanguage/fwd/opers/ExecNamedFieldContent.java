package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class ExecNamedFieldContent extends ComNamedFieldContent {
    private final ExecRootBlock declaring;
    public ExecNamedFieldContent(AnaNamedFieldContent _ana, ExecRootBlock _declaring) {
        super(_ana.getName(), _ana.getType(), _ana.getIdClass());
        declaring = _declaring;
    }

    public ExecRootBlock getDeclaring() {
        return declaring;
    }
}
