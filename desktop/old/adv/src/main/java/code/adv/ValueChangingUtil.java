package code.adv;

public final class ValueChangingUtil {
    private ValueChangingUtil() {
    }
    public static void act(ValueChanging _value) {
        if (_value.skip()) {
            return;
        }
        _value.act();
    }
}
