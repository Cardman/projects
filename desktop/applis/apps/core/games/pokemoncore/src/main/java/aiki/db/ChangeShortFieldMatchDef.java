package aiki.db;

public final class ChangeShortFieldMatchDef implements ChangeShortFieldMatch {
    private final ChangeShortField changeStringField;

    public ChangeShortFieldMatchDef(ChangeShortField _c) {
        this.changeStringField = _c;
    }

    @Override
    public boolean match(int _v) {
        return changeStringField.value() == _v;
    }
}
