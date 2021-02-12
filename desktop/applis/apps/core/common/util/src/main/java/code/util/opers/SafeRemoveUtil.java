package code.util.opers;

import code.util.ints.SafeRemove;

public final class SafeRemoveUtil {
    private SafeRemoveUtil() {
    }
    public static void safeRemove(SafeRemove _safe, int _index) {
        if (_safe.ok(_index)) {
            _safe.removeItemAt(_index);
        }
    }
}
