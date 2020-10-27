package code.expressionlanguage.exec;

import code.expressionlanguage.structs.ClassMetaInfo;
import code.util.core.StringUtil;
import code.util.ints.Comparing;

public class ClassNameCmp implements Comparing<ClassMetaInfo> {

    @Override
    public int compare(ClassMetaInfo _one, ClassMetaInfo _two) {
        return StringUtil.compareStrings(_one.getName(),_two.getName());
    }

}
