package code.expressionlanguage.opers.util;

import code.util.ints.SortedEdge;

public class SortedClassField implements SortedEdge<SortedClassField> {

    private int order = -1;

    private final ClassField classField;

    public SortedClassField(ClassField _classField) {
        classField = _classField;
    }

    @Override
    public boolean eq(SortedClassField _g) {
        return classField.eq(_g.classField);
    }

    @Override
    public void setOrder(int _o) {
        order = _o;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public ClassField getClassField() {
        return classField;
    }
}
