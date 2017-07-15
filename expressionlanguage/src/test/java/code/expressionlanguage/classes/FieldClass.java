package code.expressionlanguage.classes;
import code.expressionlanguage.AccEl;

public class FieldClass {

    @AccEl
    private int field;

    public FieldClass(int _field) {
        field = _field;
    }

    public int getField() {
        return field;
    }
}
