package aiki.facade.enums;
import code.util.StringList;
import code.util.ints.Listable;

public enum SelectedBoolean {
    YES, NO, YES_AND_NO;

    public static SelectedBoolean getBoolByName(String _env) {
        for (SelectedBoolean e : values()) {
            if (StringList.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return YES_AND_NO;
    }
}
