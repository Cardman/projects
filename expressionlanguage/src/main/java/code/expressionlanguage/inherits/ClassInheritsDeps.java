package code.expressionlanguage.inherits;

import code.expressionlanguage.methods.RootBlock;
import code.util.StringList;
import code.util.ints.SortedEdge;

public final class ClassInheritsDeps implements SortedEdge<ClassInheritsDeps> {

    private int order = -1;

    private final String classField;

    private final RootBlock rootBlock;

    public ClassInheritsDeps(String _classField, RootBlock _rootBlock) {
        classField = _classField;
        rootBlock = _rootBlock;
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

    public RootBlock getRootBlock() {
        return rootBlock;
    }

    public String getClassField() {
        return classField;
    }

}
