package code.adv;

public final class ValueChangingSecondUtil {
    private ValueChangingSecondUtil() {
    }
    public static void act(ValueChangingSecond _v,int _value) {
        if (_v.getValue() == _value) {
            _v.act();
        }
    }
}
