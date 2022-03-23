package code.adv;

public final class BoolIntChoiceUtil {
    private BoolIntChoiceUtil(){
    }
    public static int choice(AbsBoolIntChoice _choice, int _one, int _two) {
        if (_choice.ok()) {
            return _one;
        }
        return _two;
    }
}
