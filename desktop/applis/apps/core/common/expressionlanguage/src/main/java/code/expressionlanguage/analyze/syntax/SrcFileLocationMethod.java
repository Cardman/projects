package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;

public final class SrcFileLocationMethod implements SrcFileLocation {
    private final NamedFunctionBlock method;

    public SrcFileLocationMethod(NamedFunctionBlock _m) {
        this.method = _m;
    }

    @Override
    public String getFileName() {
        return getMethod().getFile().getFileName();
    }

    @Override
    public int getIndex() {
        return getMethod().getNameOffset();
    }

    public NamedFunctionBlock getMethod() {
        return method;
    }
}
