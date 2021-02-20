package code.expressionlanguage.common;

import code.maths.litteral.AbstractWordSplit;

public final class AdvWordSplit extends AbstractWordSplit {
    @Override
    protected boolean isWordChar(char _char) {
        return StringExpUtil.isDollarWordChar(_char);
    }
}
