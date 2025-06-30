package code.gui.document;

import aiki.game.fight.util.*;
import code.gui.*;

public final class MoveTargetDefValue implements AbsDefValue<MoveTarget> {
    @Override
    public MoveTarget defValue() {
        return MoveTarget.def();
    }
}
