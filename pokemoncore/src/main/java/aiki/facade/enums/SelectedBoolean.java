package aiki.facade.enums;
import code.util.StringList;
import code.util.ints.Listable;

public enum SelectedBoolean {
    YES(true),NO(false),YES_AND_NO;

    private final Boolean selected;

    SelectedBoolean() {
        selected = null;
    }
    SelectedBoolean(boolean _selected) {
        selected = _selected;
    }
    public static boolean equalsSet(Listable<SelectedBoolean> _list1,Listable<SelectedBoolean> _list2) {
        for (SelectedBoolean a: _list2) {
            boolean contains_ = false;
            for (SelectedBoolean b: _list1) {
                if (a == b) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        for (SelectedBoolean a: _list1) {
            boolean contains_ = false;
            for (SelectedBoolean b: _list2) {
                if (a == b) {
                    contains_ = true;
                    break;
                }
            }
            if (!contains_) {
                return false;
            }
        }
        return true;
    }
    public static SelectedBoolean getBoolByName(String _env) {
        for (SelectedBoolean e: values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return YES_AND_NO;
    }
    public boolean isSelected() {
        return selected;
    }
    public Boolean getSelected() {
        return selected;
    }
    public SelectedBoolean neg() {
        if (this == YES) {
            return NO;
        }
        if (this == NO) {
            return YES;
        }
        return YES_AND_NO;
    }
}
