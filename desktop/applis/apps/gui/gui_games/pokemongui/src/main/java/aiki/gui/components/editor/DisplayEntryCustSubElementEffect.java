package aiki.gui.components.editor;

import code.gui.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementEffect<E> implements DisplayEntryCustSubElement<E> {

    @Override
    public IdList<SubscribedTranslation> buildSub() {
        return new IdList<SubscribedTranslation>();
    }

    @Override
    public DisplayEntryCust<Integer, E> buildDisplay() {
        return new DisplayEntryCustEffect<E>();
    }

    @Override
    public Comparing<E> buildCmp() {
        return null;
    }
}
