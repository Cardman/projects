package aiki.gui.components.editor;

import code.maths.*;

public final class RateTechnicalCopier implements AbsTechnicalCopier<Rate> {
    @Override
    public Rate copy(Rate _e) {
        return new Rate(_e);
    }
}
