package code.expressionlanguage.classes;
import code.expressionlanguage.AccEl;

public class FieldFieldClass {

    @AccEl
    private FieldClass field;

    public FieldFieldClass(FieldClass _field) {
        field = _field;
    }

    public FieldClass getField() {
        return field;
    }
}
