package aiki.gui.components.editor;

import code.gui.DisplayEntryCust;
import code.util.*;
import code.util.ints.Comparing;

public interface DisplayEntryCustSubElement<K> {
    IdList<SubscribedTranslation> buildSub();

    DisplayEntryCust<Integer,K> buildDisplay();

    Comparing<K> buildCmp();
}
