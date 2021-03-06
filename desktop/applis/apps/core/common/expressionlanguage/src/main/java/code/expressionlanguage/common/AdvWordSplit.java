package code.expressionlanguage.common;

import code.maths.litteralcom.AbstractWordSplit;

public final class AdvWordSplit extends AbstractWordSplit {
    @Override
    protected boolean isWordChar(char _char) {
        return StringExpUtil.isDollarWordChar(_char);
    }
}
