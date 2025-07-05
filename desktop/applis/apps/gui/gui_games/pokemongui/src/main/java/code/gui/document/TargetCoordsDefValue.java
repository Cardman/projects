package code.gui.document;

import aiki.game.fight.*;
import code.gui.*;

public final class TargetCoordsDefValue implements AbsDefValue<TargetCoords> {
    @Override
    public TargetCoords defValue() {
        return TargetCoords.def();
    }
}
