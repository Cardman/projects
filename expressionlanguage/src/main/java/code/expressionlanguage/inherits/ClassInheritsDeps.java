package code.expressionlanguage.inherits;

import code.util.StringList;
import code.util.ints.SortedEdge;

public final class ClassInheritsDeps implements SortedEdge<ClassInheritsDeps> {

    private int order = -1;

    private final String classField;

    public ClassInheritsDeps(String _classField) {
        classField = _classField;
    }

    @Override
    public boolean eq(ClassInheritsDeps _g) {
        return StringList.quickEq(classField, _g.classField);
    }

    @Override
    public void setOrder(int _o) {
        order = _o;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public String getClassField() {
        return classField;
    }

}
