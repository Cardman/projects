package aiki.gui.components.editor;

import code.gui.*;

public final class DisplayEntryCustEffect<E> implements DisplayEntryCust<Integer, E> {

    @Override
    public String display(Integer _k, E _v) {
        return Long.toString(_k);
    }
}
