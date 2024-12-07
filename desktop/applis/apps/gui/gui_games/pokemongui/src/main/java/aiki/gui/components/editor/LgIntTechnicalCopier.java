package aiki.gui.components.editor;

import code.maths.*;

public final class LgIntTechnicalCopier implements AbsTechnicalCopier<LgInt> {
    @Override
    public LgInt copy(LgInt _e) {
        return new LgInt(_e);
    }
}
