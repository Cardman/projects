package aiki.gui.components.editor;


import code.util.*;

public final class StringListTechnicalCopier implements AbsTechnicalCopier<StringList> {
    @Override
    public StringList copy(StringList _e) {
        return new StringList(_e);
    }
}
