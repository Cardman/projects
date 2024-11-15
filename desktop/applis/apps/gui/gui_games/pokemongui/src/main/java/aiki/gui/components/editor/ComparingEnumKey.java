package aiki.gui.components.editor;

import code.gui.*;
import code.util.AbsMap;
import code.util.core.StringUtil;
import code.util.ints.*;

public final class ComparingEnumKey<K,V> implements Comparing<EditedCrudPair<K, V>>  {
    private final AbsMap<K,String> messages;

    public ComparingEnumKey(AbsMap<K, String> _m) {
        this.messages = _m;
    }

    @Override
    public int compare(EditedCrudPair<K, V> _one, EditedCrudPair<K, V> _two) {
        return StringUtil.compareStrings(messages.getVal(_one.getKey()),messages.getVal(_two.getKey()));
    }
}
