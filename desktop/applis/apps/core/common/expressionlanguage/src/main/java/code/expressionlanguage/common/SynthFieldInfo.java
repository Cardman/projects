package code.expressionlanguage.common;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class SynthFieldInfo {
    private final ClassField classField;
    private final RootBlock rootBlock;

    public SynthFieldInfo(ClassField _c, RootBlock _r) {
        this.classField = _c;
        this.rootBlock = _r;
    }

    public RootBlock getRootBlock() {
        return rootBlock;
    }

    public ClassField getClassField() {
        return classField;
    }

}
