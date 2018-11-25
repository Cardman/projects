package code.expressionlanguage;

import code.expressionlanguage.structs.ClassMetaInfo;
import code.util.ints.Comparing;

public class ClassNameCmp implements Comparing<ClassMetaInfo> {

    @Override
    public int compare(ClassMetaInfo _one, ClassMetaInfo _two) {
        return _one.getName().compareTo(_two.getName());
    }

}
