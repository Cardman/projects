package aiki.gui.components.editor;

import code.gui.*;
import code.util.*;
import code.util.ints.*;

public final class DisplayEntryCustSubElementString implements DisplayEntryCustSubElement<String> {

    @Override
    public IdList<SubscribedTranslation> buildSub() {
        return new IdList<SubscribedTranslation>();
    }

    @Override
    public DisplayEntryCust<Integer, String> buildDisplay() {
        return new DisplayEntryCustString();
    }

    @Override
    public Comparing<String> buildCmp() {
        return null;
    }
}
