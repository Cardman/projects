package code.expressionlanguage.common;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class SynthFieldInfo {
    private final ClassField classField;
    private final RootBlock rootBlock;
    private final int rootBlockNb;

    public SynthFieldInfo(ClassField _c, RootBlock _r) {
        this(_c,_r,nb(_r));
    }

    private SynthFieldInfo(ClassField _c, RootBlock _r, int _nb) {
        this.classField = _c;
        this.rootBlock = _r;
        this.rootBlockNb = _nb;
    }
    public static int nb(RootBlock _r) {
        if (_r == null) {
            return -1;
        }
        return _r.getNumberAll();
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
