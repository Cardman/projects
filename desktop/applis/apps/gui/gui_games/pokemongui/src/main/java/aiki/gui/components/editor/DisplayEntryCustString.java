package aiki.gui.components.editor;

import code.gui.DisplayEntryCust;

public final class DisplayEntryCustString implements DisplayEntryCust<Integer, String> {

    @Override
    public String display(Integer _k, String _v) {
        return _k+":"+_v;
    }
}
