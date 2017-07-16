package code.maths.montecarlo;

public class NotAccessibleClass {

    private int primitiveField;

    public int getPrimitiveField() {
        return primitiveField;
    }

    public void setPrimitiveField(int _primitiveField) {
        primitiveField = _primitiveField;
    }
}
