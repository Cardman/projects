package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.stds.StandardNamedFunction;

public final class SrcFileLocationStdMethod implements SrcFileLocation {
    private final StandardNamedFunction std;

    public SrcFileLocationStdMethod(StandardNamedFunction _s) {
        this.std = _s;
    }

    @Override
    public String getFileName() {
        return "";
    }

    @Override
    public int getIndex() {
        return 0;
    }

    public StandardNamedFunction getStd() {
        return std;
    }
}
