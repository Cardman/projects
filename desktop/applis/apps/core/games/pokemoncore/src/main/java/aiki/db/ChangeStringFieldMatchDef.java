package aiki.db;

import code.util.core.StringUtil;

public final class ChangeStringFieldMatchDef implements ChangeStringFieldMatch {
    private final ChangeStringField changeStringField;

    public ChangeStringFieldMatchDef(ChangeStringField _c) {
        this.changeStringField = _c;
    }

    @Override
    public boolean match(String _v) {
        if (changeStringField == null) {
            return false;
        }
        return StringUtil.quickEq(changeStringField.value(),_v);
    }
}
