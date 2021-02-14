package code.adv;

public final class SafeRemoveAdvUtil {
    private SafeRemoveAdvUtil() {
    }
    public static void safeRemove(SafeRemoveAdv _safe, int _index) {
        if (_index > -1&&_safe.size() > _index) {
            _safe.removeItemAt(_index);
        }
    }
}
