package aiki.gui.components.editor;

import code.gui.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementInt implements DisplayEntryCustSubElement<Integer> {

    @Override
    public IdList<SubscribedTranslation> buildSub() {
        return new IdList<SubscribedTranslation>();
    }

    @Override
    public DisplayEntryCust<Integer, Integer> buildDisplay() {
        return new DisplayKeyOnlyInteger();
    }

    @Override
    public Comparing<Integer> buildCmp() {
        return new IntComparator();
    }
}
