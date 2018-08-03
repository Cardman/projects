package code.expressionlanguage;

import code.expressionlanguage.opers.util.ClassMetaInfo;

public class ClassNameCmp {

    private final ClassMetaInfo meta;

    public ClassNameCmp(ClassMetaInfo _meta) {
        meta = _meta;
    }

    public ClassMetaInfo getMeta() {
        return meta;
    }
}
