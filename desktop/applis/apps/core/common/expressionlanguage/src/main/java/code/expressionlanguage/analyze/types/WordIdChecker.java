package code.expressionlanguage.analyze.types;

import code.expressionlanguage.common.StringExpUtil;

public final class WordIdChecker implements AbsIdChecker {
    @Override
    public boolean match(String _i) {
        return StringExpUtil.isTypeLeafPart(_i);
    }
}
