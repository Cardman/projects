package code.expressionlanguage;

import code.util.ints.Comparing;

public class ClassNameCmpr implements Comparing<ClassNameCmp> {

    @Override
    public int compare(ClassNameCmp _one, ClassNameCmp _two) {
        return _one.getMeta().getName().compareTo(_two.getMeta().getName());
    }

}
