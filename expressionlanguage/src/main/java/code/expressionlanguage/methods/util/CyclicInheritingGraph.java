package code.expressionlanguage.methods.util;

import code.util.EqList;
import code.util.StringList;

public final class CyclicInheritingGraph extends FoundErrorInterpret {

    private static final String CLASS_NAME = "cyclic";

    private EqList<ConstructorEdge> className;

    @Override
    public String display() {
        StringList cycle_ = new StringList();
        for (ConstructorEdge c: className) {
            cycle_.add(c.getId().getSignature());
        }
        cycle_.removeDuplicates();
        return StringList.concat(super.display(),CLASS_NAME,SEP_KEY_VAL,cycle_.join(";"),SEP_INFO);
    }

    public EqList<ConstructorEdge> getClassName() {
        return className;
    }

    public void setClassName(EqList<ConstructorEdge> _className) {
        className = _className;
    }

}
