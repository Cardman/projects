package aiki.db;

import code.util.*;
import code.util.core.*;

public final class ChangeStringFieldMatchStringListContains implements ChangeStringFieldMatch {
    private final CustList<String> changeStringField;

    public ChangeStringFieldMatchStringListContains(CustList<String> _c) {
        this.changeStringField = _c;
    }

    @Override
    public boolean match(String _v) {
        return StringUtil.contains(changeStringField,_v);
    }
}
