package aiki.gui.components.editor;

import code.gui.*;

public final class DisplayKeyOnlyInteger implements DisplayEntryCust<Integer, Integer> {

    @Override
    public String display(Integer _k, Integer _v) {
        return Long.toString(_k);
    }
}
