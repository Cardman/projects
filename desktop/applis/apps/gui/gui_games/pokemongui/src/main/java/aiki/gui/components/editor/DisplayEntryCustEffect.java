package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;

public final class DisplayEntryCustEffect implements DisplayEntryCust<Integer, Effect> {

    @Override
    public String display(Integer _k, Effect _v) {
        return Long.toString(_k);
    }
}
