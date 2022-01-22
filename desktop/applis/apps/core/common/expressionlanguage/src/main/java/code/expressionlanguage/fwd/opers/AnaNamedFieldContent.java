package code.expressionlanguage.fwd.opers;

import code.expressionlanguage.analyze.blocks.RootBlock;

public final class AnaNamedFieldContent extends ComNamedFieldContent {
    private final RootBlock declaring;

    public AnaNamedFieldContent(String _name, String _type, String _idClass, RootBlock _declaring) {
        super(_name, _type, _idClass);
        this.declaring = _declaring;
    }

    public RootBlock getDeclaring() {
        return declaring;
    }

}
