package code.formathtml.errors;

import code.expressionlanguage.common.AbsCharMapping;
import code.expressionlanguage.common.StringDataUtil;

public final class UpperCharMapping implements AbsCharMapping {
    @Override
    public char map(char _ch) {
        return StringDataUtil.toUpperCase(_ch);
    }
}
