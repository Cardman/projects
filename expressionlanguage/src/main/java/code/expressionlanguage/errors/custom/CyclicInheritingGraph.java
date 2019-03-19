package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.util.ConstructorEdge;
import code.util.EqList;
import code.util.StringList;

public final class CyclicInheritingGraph extends FoundErrorInterpret {

    private static final String CLASS_NAME = "cyclic";

    private StringList className;

    @Override
    public String display(Classes _classes) {
        StringList cycle_ = new StringList();
        for (String c: className) {
            cycle_.add(c);
        }
        cycle_.removeDuplicates();
        return StringList.concat(super.display(_classes),CLASS_NAME,SEP_KEY_VAL,cycle_.join(";"),SEP_INFO);
    }

    public void setClassName(StringList _className) {
        className = _className;
    }

}
