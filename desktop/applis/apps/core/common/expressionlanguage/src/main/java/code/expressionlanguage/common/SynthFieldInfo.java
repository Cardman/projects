package code.expressionlanguage.common;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class SynthFieldInfo {
    private final ClassField classField;
    private final RootBlock rootBlock;
    private final int rootBlockNb;

    public SynthFieldInfo(ClassField _c, RootBlock _r, int _nb) {
        this.classField = _c;
        this.rootBlock = _r;
        this.rootBlockNb = _nb;
    }

    public RootBlock getRootBlock() {
        return rootBlock;
    }

    public int getRootBlockNb() {
        return rootBlockNb;
    }

    public ClassField getClassField() {
        return classField;
    }

}
