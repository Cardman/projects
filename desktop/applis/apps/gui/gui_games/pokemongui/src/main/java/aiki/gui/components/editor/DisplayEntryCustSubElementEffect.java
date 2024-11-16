package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementEffect implements DisplayEntryCustSubElement<Effect> {

    @Override
    public IdList<SubscribedTranslation> buildSub() {
        return new IdList<SubscribedTranslation>();
    }

    @Override
    public DisplayEntryCust<Integer, Effect> buildDisplay() {
        return new DisplayEntryCustEffect();
    }

    @Override
    public Comparing<Effect> buildCmp() {
        return null;
    }
}
