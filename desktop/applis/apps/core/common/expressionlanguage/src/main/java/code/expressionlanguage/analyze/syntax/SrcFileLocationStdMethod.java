package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.stds.StandardMethod;

public final class SrcFileLocationStdMethod implements SrcFileLocation {
    private final StandardMethod std;

    public SrcFileLocationStdMethod(StandardMethod _s) {
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

    public StandardMethod getStd() {
        return std;
    }
}
