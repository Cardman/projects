package aiki.gui.components.editor;

import aiki.map.places.*;
import code.gui.*;

public final class DisplayEntryCustPlace implements DisplayEntryCust<Integer, Place> {

    @Override
    public String display(Integer _k, Place _v) {
        return _k+":"+_v.getName();
    }
}
