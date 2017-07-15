package code.expressionlanguage.classes;
import code.expressionlanguage.AccEl;

public class FinalFieldClass {

    @AccEl
    private final int field;

    public FinalFieldClass(int _field) {
        field = _field;
    }

    public int getField() {
        return field;
    }
}
