package aiki.gui.components.editor;


import code.util.*;

public final class IntsTechnicalCopier implements AbsTechnicalCopier<Ints> {
    @Override
    public Ints copy(Ints _e) {
        return new Ints(_e);
    }
}
