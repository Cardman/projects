package aiki.gui.components.editor;

import aiki.util.*;
import code.util.*;

public final class ConverterCommonMap<K,V> {
    public void feed(CommonMap<K,V> _dest, AbsMap<K,V> _tree) {
        for (EntryCust<K,V> e: _tree.entryList()) {
            _dest.addEntry(e.getKey(),e.getValue());
        }
    }
}
